package com.koreait.ex03.contorller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.ex03.config.MemberConfig;
import com.koreait.ex03.domain.Member;

@Controller
public class MemberController {
	
	AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(MemberConfig.class);
	
	// Spring Version 4 부터 @GetMapping, @PostMapping 지원
	
	
	//@RequestMapping(value="/", method = RequestMethod.GET)
	@GetMapping("/")
	public String a(HttpServletRequest request) { // request 요청 실행
		
		// member1 을 만들어서 index.jsp로 보내기
		Member member1 = ctx.getBean("member1", Member.class);
		request.setAttribute("member1", member1);	// member1 request에 저장하고
											// forward 방식으로 index에 전달
		return "index";	// 기본 이동은 forward이므로 request가 전달됨
	}
	
	//@RequestMapping(value="memberView.do", method=RequestMethod.GET)
	@GetMapping("memberView1.do")
	public String b(Model model) {
		
		// Model
		// 안녕 난 request를 사용하는 클래스야 보안이 더 좋지  // Model에 저장하면 requet에 저장하는 것과 동일하다고 보면 될 것 같음
		// addAttribute()를 사용하면 실제로는 request.setAttribute() 처럼 동작함
		
		// member2을 만들어서 memberDtail.jsp로 보내기 !
		
		Member member2 = ctx.getBean("member2", Member.class);
		//					값		, 값			// addAttribute는 null값이 될 수 없지만 그 안에 있는 값은 null이 될 수 있다.
		model.addAttribute("member2", member2);
		
		return "member/memberDtail";
	}
	
	@Autowired
	@Qualifier(value="member3") // <qualifier value="member3"/>
	private Member member3;

	@GetMapping("memberView2.do")
	public String c(Model model) {
		model.addAttribute("member", member3);	
		
		return "member/memberDtail";
	}
	
	@Autowired
	@Qualifier(value="member4")
	private Member member4;
	
	@GetMapping("memberView3.do")
	public String d(Model model) {
		model.addAttribute("member", member4);
		
		return "member/memberDtail";
	}
}
