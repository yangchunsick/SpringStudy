package com.koreait.ex05.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.ex05.domain.Member;

@Controller
public class MemberController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// AJAX Data를 반환한다.
	// text/plan,  ,text/xml,  application/json
	// JSON을 반환할 수도 있다
	// 이런 등등의 데이터들을 반환한다.
	// produces = "" 무언가를 만든다 
	@ResponseBody 	// AJAX 처리 할 때는 view를 반환하지 않는다.
	@GetMapping(value = "member/v1.do", produces = "text/plain; charset=UTF-8")
	public String v1(@RequestParam(value="id") String id) {
		String result = "입력된 아이디는 " + id + "입니다";
		return result;
	}
	
	@ResponseBody
	@GetMapping(value="member/v2.do", produces = "application/json; charset=UTF-8")
	// AJAX에서 DATATYPE을 JSON TYPE으로 받을 때
	public Member v2(Member member){
		return member;	// 반환되는 bean은 data-bind에 의해서 json으로 변환됨 
	}
	
	@ResponseBody
	@GetMapping(value="member/v3.do",
				produces = "application/json; charset=UTF-8")
	public Map<String, String> v3(HttpServletRequest request){
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("id", request.getParameter("id"));
		resultMap.put("pw", request.getParameter("pw"));
		return resultMap; // 반환되는 bean은 data-bind에 의해서 json으로 변환됨 
	}
	
	@ResponseBody
	@PostMapping(value="member/v4.do",
				 produces = "application/json; charset=UTF-8")
	public Map<String, Object> v4(@RequestBody Member member){	// 전달된 JSON 데이터는 jackson data-bind에 의해서 bean을 변환됨
		// 전달되는 데이터를 처리 해야하는데 Json 방식의 데이터는
		// @RequestBody 에너테이션이 필요하다.
		Map<String, Object> map = new HashMap<String, Object>() ;
		// 타입이 섞여 있을 때 map의 value를 object로 지정한다
		map.put("id", member.getId());
		map.put("pw", member.getPw());
		map.put("addOn", 1000);
		return map;
	}
	

	
}
