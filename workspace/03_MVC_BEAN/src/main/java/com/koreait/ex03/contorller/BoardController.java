package com.koreait.ex03.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.ex03.domain.Board;

@Controller
public class BoardController {

	// ctx 없이 Bean 생성하기

	// 1. @Inject, @Resource, @Autowired와 같은 에너테이션 중 하나를 사용
	// 2. @Autowired를 가장 많이 사용
	
	// @Autowired
	// 1. 객체 타입 (class)이 일치하면 객체를 찾아서 알아서 생성한다.
	// 2. 필드, 생성자, setter를 대상으로 함
	// 3. 필드는 필드 자체에 생성, 생성자나 setter는 매개변수에 생성한다.
	
	/* (1) 필드
		1. 필드에 @Autowired 지정하기
		2. 필드마다 붙여야 함. 필드가 10개이면 10개의 @Autowired가 필요함. 그래서 자주 안 씀
	@Autowired
	private Board myBoard; // 반환 타입이 Board인 Bean을 찾아서 가져와서 myBoard에 주입 
		  // ↑ 이놈이 타입임     					// DI Dependency Injection 디펜더시인젝션 
	*/
	
	/*	(2) 생성자
		1. 생성자를 만들면, 생성자의 매개변수로 자동으로 주입된다.
		2. 생성자에 @Autowired를 생략해도 된다.
	private Board myBoard;
	
	public BoardController(Board myBoard) {	// 생성자를 만들면, 생성자의 매개변수로 자동 주입된다.
		super();							// 생성자를 만들면 매개변수로 자동으로 주입이 된다 ?
		this.myBoard = myBoard;
	}
	*/
	/*
	private Board myBoard;
	(3)
	1. setter에 @Autowired가 꼭 붙어 있어야 하고
	2. setter의 메소드명은 아무렇게 작성해도 상관 없다.
	2. @Autowired만 붙어 있다면 OK인 것 같음
	@Autowired // 생략할 수 없다.
	public void setBoard(Board myBoard) {	// 매개변수로 자동 주입 된다.
		System.out.println(myBoard);
		this.myBoard = myBoard;
	}
	*/
	
	// 	! Bean을 이용한 
	// @Autowired
	// private Board myBoard;
	
	/* 	! 생성자를 이용한
	// 1. 필드 생성
	//private Board myBoard;
	
	// 2. 생성자 생성
	public BoardController(Board myBoard) {
		super();
		this.myBoard = myBoard;
	}
	*/
	
	/*
	 	! Setter를 이용한
 	// 1. 필드 생성
	private Board myBoard;
	// 2. setter 생성
	@Autowired	// !!!! setter를 이용할 때는 @Autowired가 꼭 필요함
	public void setMyBoard(Board myBoard) {
		this.myBoard = myBoard;
	}
	 */
	// 1. 필드 생성
	private Board myBoard;
	// 2. setter 생성
	@Autowired	// !!!! setter를 이용할 때는 @Autowired가 꼭 필요함
	public void setMyBoard(Board myBoard) {
		this.myBoard = myBoard;
	}

	@GetMapping("boardView.do")
	public String a(Model model) { // String은 return 타입
		model.addAttribute("board", myBoard);
		return "board/boardDtail";
	}
	
}
