package com.koreait.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.ex04.domain.Member;
import com.koreait.ex04.domain.MemberBuilder;

@Controller // annotation 에너테이션
public class MemberController {
	// 자바에서 중괄호는 배열의 초기화를 뜻 한다.
	@GetMapping({"/","index.do"})
	// 메소드의 이름은 의미 없다..~
	public String a() {
		return "index";
	}
	
	// ("member/v1.do") 괄호 안에는 맵핑을 적어준다..~
	// @GetMapping("/member/v1.do")	// ("/")로 시작해도 된다.
	@GetMapping("member/v1.do")
	public String b() {
		// return "/member/memberDetail";
		return "member/memberDetail";
	}
	
	@GetMapping("member/v2.do")
	public String c(HttpServletRequest request) {	// 파라미터 처리는 매개변수가 해야 한다. () 괄호 안에서 받아야 함
		Long idx = Long.parseLong(request.getParameter("idx"));
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		// Member 생성 - 1
		Member member1 = new Member();
		member1.setIdx(idx);
		member1.setId(id);
		member1.setName(name);
		
		// Member 생성 - 2
		Member member2 = new Member(idx, id, name);
		
		// Member 생성 - 3 (Builder Pattern 이용하기)
		// Builder Pattern 이용하는 이유
		/* https://mangkyu.tistory.com/163
		  - 장점
		  	1. 필요한 데이터만 설정할 수 있음
		  	2. 유연성을 확보 할 수 있음
		  	3. 가독성을 높일 수 있음
		  	4. 불변성을 확보할 수 있음 (불변성 : 변하지 않는 성질)
		*/
		Member member3 = new MemberBuilder()
				.setIdx(idx)
				.setId(id)
				.setName(name)
				.build();
		// request를 이용해서 Member 넘기기
		request.setAttribute("member", member3);
		
		return "member/memberDetail";
	}
	
	@GetMapping("member/v3.do")	// 속성은 생략 가능 						// @RequestParam(value="파라미터명") 저장할 변수 이름
	public String d(@RequestParam(value="idx") Long idx,     			  	// idx 라는 Param값을 Long idx에 저장해라
					@RequestParam(value="id") String id, 	 			  	// id 라는 Param값을 String id에 저장해라
					@RequestParam(value="name") String name, Model model) { // name 라는 Param값을 String name에 저장해라
		
		//Model은 JSP에 값을 넘기는 역할이다.
		model.addAttribute("member", new Member(idx, id, name));
		
		return "member/memberDetail";
	}
	
	@GetMapping("member/v4.do")						//  꼭 필요한가 ? 없으면 안 받겠다 // 기본값 
	public String e(@RequestParam(value = "idx", required = false, defaultValue = "999") Long idx,
					@RequestParam(value = "id", required = false, defaultValue = "error") String id,
					@RequestParam(value="name", required = false, defaultValue = "error") String name, Model model) {
		
		model.addAttribute("member", new Member(idx, id, name));
		return "member/memberDetail";
	}
	
	// <a href="member/v5.do?idx=1&id=user&name=chun">회원 5</a>
	@GetMapping("member/v5.do")
	public String f(Member member,	// Member 클래스의 setter가 파라미터를 모두 받아 준다.
					Model model) {
		model.addAttribute("member", member);	// Member 객체에 저장
		return "member/memberDetail";		
	}
	
	//	<a href="member/v5.do?idx=1&id=user&name=chun">회원 5</a><br>
	@GetMapping("member/v6.do")	
	public String g(@ModelAttribute(value = "member") Member member) { // @ModelAttribute 엘리먼트가 member 객체를 저장할 건데 객체는 Member의 member 객체이다.
		return "member/memberDetail";
	}
	
	
	
	
	
	
}
