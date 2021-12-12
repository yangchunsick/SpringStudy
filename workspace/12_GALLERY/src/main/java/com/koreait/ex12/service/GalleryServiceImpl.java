package com.koreait.ex12.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.ex12.domain.Gallery;
import com.koreait.ex12.repository.GalleryRepository;

import net.coobird.thumbnailator.Thumbnails;

public class GalleryServiceImpl implements GalleryService {

	// 공통적으로 필요한건 repository이다.
	// sqlSession이 자동적으로 필요함
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/* 목록 */
	@Override
	public List<Gallery> selectGalleryList() {
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class);
		return repository.selectGalleryList();
	}
	
	/* 상세보기 */
	@Override
	public Gallery selectGalleryByNo(Long no) {
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class);
		return repository.selectGalleryByNo(no);
	}
	
	/* 추가 */
	@Override
	public void insertGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 첨부파일 처리 : 서버에 파일 저장 + DB에 정보 저장
		
		// DB로 보낼 Gallery gallery
		Gallery gallery = new Gallery();
		gallery.setWriter(multipartRequest.getParameter("writer"));
		gallery.setTitle(multipartRequest.getParameter("title"));
		gallery.setContent(multipartRequest.getParameter("content"));
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));	// 이게 null 이면
		gallery.setIp(opt.orElse(multipartRequest.getRemoteAddr()));	// 이걸로 할게요
		
		// 서버에 파일 저장
		try {
			MultipartFile file =  multipartRequest.getFile("file");
			if(file != null && !file.isEmpty()) { // 첨부가 있으면 (둘 다 필요)
	
				// UUID : Universal Unique IDentifier - 범용 고유 식별자
				// UUID : ????????-????-????-????????????: 하이픈 제외하면 32자리
				// UUID를 파일명으로 사용하면,
				// 1. 파일명 인코딩 해결
				// 2. 파일명 중복 저장 해결
				
				// 원래 올린 첨부 파일의 이름 : file.getOriginalFilename();
				String origin = file.getOriginalFilename();
				
				 // 첨부 파일의 확장자 [".jpg", ".jpeg", ".gif", ".png"];
				String extName = origin.substring(origin.lastIndexOf("."));
				
				// 모든 -(하이픈)을 찾아서 빈 문자열로 만들겠다.
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				// 저장될 파일명(saved) 결정
				String saved = uuid + extName;
				
				// 저장될 경로 path;
				// resources/upload/2021/12/09
				String sep = Matcher.quoteReplacement(File.separator);
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				String path = "resources" + sep + "upload" + sep +sdf.format(new Date()).replaceAll("-", sep);
				String realPath = multipartRequest.getServletContext().getRealPath(path);
				
				// 저장될 경로에 디렉터리 만들기
				// 없으면 새로 만들어야 한다.
				File dir = new File(realPath); // 저장될 경로를 찾고
				if(dir.exists() == false) { // 디렉터리가 없으면
					dir.mkdirs(); // 디렉터리들을 만든다.
				}
				
				// * 첨부파일 서버에 업로드
				File uploadFile = new File(realPath, saved); // new File(경로, 파일)
				file.transferTo(uploadFile); // 실제 업로드 진행 코드			
			
				
				// * 썸네일 이미지 생성
			
					Thumbnails.of(uploadFile)
					.size(150, 150)
					.toFile(new File(realPath, "s_" + saved));				
			
				
				// 첨부가 있으면 DB에 origin, saved 저장
				gallery.setPath(path);
				gallery.setOrigin(origin);
				gallery.setSaved(saved);
							
			// 첨부가 없으면
			}else {
				
				// 첨부가 없으면 origin, saved 빈 문자열 처리
				gallery.setPath("");
				gallery.setOrigin("");
				gallery.setSaved("");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class);
		int result = repository.insertGallery(gallery);
		message(result, response, "새 갤러리가 등록되었습니다.", "등록 실패", "/ex12/gallery/selectGalleryList");
		
	}
	

	/* 수정 */
	@Override
	public void updateGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// DB로 보낼 Gallery gallery
		Gallery gallery = new Gallery();
		gallery.setNo(Long.parseLong(multipartRequest.getParameter("no")));
		gallery.setTitle(multipartRequest.getParameter("title"));
		gallery.setContent(multipartRequest.getParameter("content"));
		
		try {
			
			// 기존의 첨부파일 정보
			String path = multipartRequest.getParameter("path");
			String realPath = multipartRequest.getServletContext().getRealPath(path);
			String origin = multipartRequest.getParameter("origon");
			String saved = multipartRequest.getParameter("saved");
			
			// 변경할 첨부 파일
			MultipartFile newFile = multipartRequest.getFile("newFile");
			
			// 변경할 첨부가 있으면
			if(newFile != null && newFile.isEmpty() == false) {
			
				// 기존 첨부파일/ 썸네일 지우기
				File file = new File(realPath, saved);
				if(file != null && file.exists()) { // 기존에 파일이 있는지 확인하고
					file.delete();					 // 삭제
				}
				
				File thumb = new File(realPath, "s_" + saved);
				if(thumb != null && thumb.exists()) {
					thumb.delete();
				}
				
				// 새로운 첨부파일/ 썸네일 저장하기
				
				// 기존 첨부가 없는 경우 path가 없어서 새로 생성해야 함
				
				// 기존 첨부가 없는 경우 작성일 기준으로 경로 생성
				if(path.isEmpty()) {
					String created = multipartRequest.getParameter("created");
					// 저장될 경로 path;
					String sep = Matcher.quoteReplacement(File.separator);
					path = "resources" + sep + "upload" + sep + created.replaceAll("-", sep);
					realPath = multipartRequest.getServletContext().getRealPath(path);
					
					// 저장될 경로에 디렉터리 만들기
					// 없으면 새로 만들어야 한다.
					File dir = new File(realPath); // 저장될 경로를 찾고
					if(dir.exists() == false) { // 디렉터리가 없으면
						dir.mkdirs(); // 디렉터리들을 만든다.
					}

				}
				// 
				
				// 첨부 정보
				String newOrigin = newFile.getOriginalFilename();
				String extName = newOrigin.substring(newOrigin.lastIndexOf("."));
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String newSaved = uuid + extName;
			
				File uploadFile = new File(realPath, newSaved); // new File(기존 경로, 파일)
				newFile.transferTo(uploadFile); // 실제 업로드 진행 코드	
				
				// 썸네일
				Thumbnails.of(uploadFile)
				.size(150, 150)
				.toFile(new File(realPath, "s_" + newSaved));
				
				// DB 변경 사항 저장
				gallery.setPath(path);
				gallery.setOrigin(newOrigin);
				gallery.setSaved(newSaved);
			}
			
			
			// 변경할 첨부가 없으면 // 기존의 첨부파일 정보로 수정
			else {
				gallery.setPath(path);
				gallery.setOrigin(origin);
				gallery.setSaved(saved);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class);
		int result = repository.updateGallery(gallery);
		message(result, response, "수정 완료", "수정 실패", "/ex12/gallery/selectGalleryByNo?no=" + multipartRequest.getParameter("no"));
		
	}

	/* 삭제 */
	@Override
	public void deleteGalery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		 
		// 첨부 삭제
		String path = multipartRequest.getParameter("path");
		String realPath = multipartRequest.getServletContext().getRealPath(path);
		String saved = multipartRequest.getParameter("saved");
		
		// 파일의 경로를 찾고 있으면 지운다..!
		File file = new File(realPath, saved);
		if(file != null && file.exists()) {
			file.delete();
		}
		
		File thumbnail = new File(realPath, "s_" + saved);
		if(thumbnail != null && thumbnail.exists()) {
			thumbnail.delete();
		}
		
		// DB 삭제 
		Long no = Long.parseLong(multipartRequest.getParameter("no"));
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class);
		int result = repository.deleteGalery(no);
		message(result, response, "갤러리가 삭제 되었습니다.", "삭제 실패", "/ex12/gallery/selectGalleryList");
		
	}

	/* 첨부 다운로드 */
	@Override
	public void download(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("UTF-8");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드 할 파일 정보
		String path = request.getParameter("path");						 // 경로
		String realPath = request.getServletContext().getRealPath(path); // 실제 경로
		String saved = request.getParameter("saved");					 // 저장되어 있는 파일 이름
		
		// 사용자들이 다운로드 할 때 생성될 파일 이름
		String origin = request.getParameter("origin");
		
		// 다운로드 할 파일	// 경로	, 저장되어 있는 파일명
		File file = new File(realPath, saved);
		
		// 다운로드란 ?
		// 다운로드 할 File을 읽어서		 		InputStream
		// 사용자에게 그대로 응답하는 것이다.		OutputStream
		
		BufferedInputStream bis = null; // 다운로드 할 파일과 연결
		BufferedOutputStream bos = null;
		
		try {
			
			
			// 다운로드를 처리할 수 있는 response로 만들기
			response.setHeader("Content-Type", "application/x-msdownload"); // 다운로드 받는 타입 ?
			//											다운로드 받을 이름 : origin			한글로 작성된 파일을 한국어로 번역
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(origin, "UTF-8").replaceAll("\\+", " "));
			// 					길이 지정
			response.setHeader("Content-Length", file.length() + ""); // 빈 문자열을 더해주면 String이 되는 마법..☆
			
			// 다운로드 할 파일을 읽어서 출력할 스트림 생성
			bis = new BufferedInputStream(new FileInputStream(file)); // 다운로드 할 파일을 bis에 읽어줌
			bos = new BufferedOutputStream(response.getOutputStream()); // 사용자에게 보내주기 위해 출력 스트림을 사용
			
			// 스프링의 파일 복사
			FileCopyUtils.copy(bis, bos);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if(bos != null) bos.close();
				if(bis != null) bis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
