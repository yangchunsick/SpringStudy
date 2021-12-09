package com.koreait.ex12.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
				try {
					file.transferTo(uploadFile); // 실제 업로드 진행 코드			
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				// * 썸네일 이미지 생성
				try {
					Thumbnails.of(uploadFile)
					.size(150, 150)
					.toFile(new File(realPath, "s_" + saved));				
				}catch(Exception e) {
					e.printStackTrace();
				}
				
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

	@Override
	public int updateGallery(Gallery gallery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteGalery(Long no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
