package com.koreait.ex12.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.ex12.service.GalleryService;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	// Config와 맞춰야 함
	/*@Autowired*/
	private GalleryService service;
	
	// @Autowired 에너테이션을 사용하지 않고 AutoWired 하는 방법
	// 생성자의 매개변수에서 AutoWired가 자동으로 걸린다.
	public GalleryController(GalleryService service) {
		super();
		this.service = service;
	}

	// 목록으로 이동
	@GetMapping(value = "selectGalleryList")
	public String selectGalleryList(Model model) {
		model.addAttribute("list", service.selectGalleryList());
		return "gallery/list";
	}
	
	// 추가 화면으로 이동
	@GetMapping(value = "insertPage")
	public String insertPage() {
		return "gallery/insert";
	}
	
	// 추가
	@PostMapping(value = "insertGallery")
	public void insertGallery(MultipartHttpServletRequest multipartRequest,
								HttpServletResponse response) {
		service.insertGallery(multipartRequest, response);
	}
	
	// 상세보기
	@GetMapping(value = "selectGalleryByNo")
	public String selectGalleryByNo(@RequestParam("no") Long no, Model model) {
		model.addAttribute("gallery", service.selectGalleryByNo(no));
		return "gallery/detail";
	}
	
	// 첨부파일 다운로드
	@PostMapping(value = "download")
	public void download (HttpServletRequest request, HttpServletResponse response) { // 다운로드를 받을 때 response는 필수이다.
		service.download(request, response);		
	}
	
	// 수정
	@PostMapping(value = "updateGallery")
	public void updateGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		service.updateGallery(multipartRequest, response);
	}
	
	
	// 삭제
	@PostMapping(value = "deleteGallery")
	public void deleteGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		service.deleteGalery(multipartRequest, response);
	}
	
}
