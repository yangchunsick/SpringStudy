package com.koreait.ex08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.ex08.domain.Contact;
import com.koreait.ex08.service.ContactService;

@Controller
@RequestMapping(value = "contact")
public class ContactController {

	// field
	@Autowired
	private ContactService service;
	
	
	// method
	@GetMapping(value="findAllContact")
	public String findAllContact(Model model) {
		model.addAttribute("list", service.findAllContact());
		return "contact/list";
	}
	
	// 연락처 추가 화면으로 이동
	@GetMapping(value = "contactPage")
	public String contactpage() {
		return "contact/insert";
		
	}
	
	// 연락처 추가후 목록으로 이동
	// 성공 실패 처리를 안 함
	@PostMapping(value = "addContact")
	public String addContact(Contact contact) {
		service.addContact(contact);
		return "redirect: findAllContact";
	}
	
	// 상세
	@GetMapping(value = "findContact")
	public String findContact(Contact contact, Model model) {
		model.addAttribute("contact", service.findContact(contact));
		return "contact/detail";
	}
	
	// 수정
	@GetMapping(value = "updateContact")
	public String updateContact(Contact contact) {
		service.updateContact(contact);
		return "redirect:findContact?no=" + contact.getNo();
	}
	
	// 삭제
	@GetMapping(value = "deleteContact")
	public String deleteContact(Contact contact) {
		service.deleteContact(contact);
		return "redirect:findAllContact";
	}
	
}
