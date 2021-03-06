package com.koreait.ex11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.ex11.domain.Notice;
import com.koreait.ex11.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {

	@Autowired
	private NoticeService service;

	/* 목록 */
	@GetMapping(value = "selectNoticeList")
	public String selectNotice(Model model) {
		model.addAttribute("list", service.selectNoticeList());
		return "notice/list";
	}
	
	/* 상세보기 */
	@GetMapping(value = "selectNoticeByNo")
	public String selectNoticeByNo(@RequestParam("no") Long no, Model model) {
		model.addAttribute("notice", service.selectNoticeByNo(no));
		return "notice/detail";
	}
	
	/* 추가 화면 */
	@GetMapping(value = "noticePage")
	public String noticePage(Model model) {
		return "notice/insert";
	}
	
	/* 추가 */
	@PostMapping(value = "insertNotice")
	public String insertNotice(Notice notice, Model model) {
		service.insertNotice(notice);
		return "redirect:selectNoticeList";
	}
	
	/* 수정 */
	@GetMapping(value = "updateNotice")
	public String updateNotice(Notice notice) {
		service.updateNotice(notice);
		return "redirect:selectNoticeList";
	}
	
	/* 삭제 */
	@GetMapping(value = "deleteNotice")
	public String deleteNotice(Long no) {
		service.deleteNotice(no);
		return "redirect:selectNoticeList";
	}
}
