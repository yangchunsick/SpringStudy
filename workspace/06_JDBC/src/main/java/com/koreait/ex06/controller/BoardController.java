package com.koreait.ex06.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.ex06.domain.Board;
import com.koreait.ex06.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	// 로그 생성기
	// System.out.println() 대체
	// 				     인터페이스 
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// BoardService Interface
	@Autowired
	private BoardService service;
	
	// 게시판 목록
	@GetMapping(value = "selectBoardList.do")
	public String selectBoardList(Model model) { // Model : JSP로 값을 넘긴다.
		logger.info("selectBoardList() 호출");	// console에 정보가 찍힘
		// service = new BoardServiceImpl(s);
		List<Board> list = service.selectBoardList();
		logger.info(list.toString());	// board 목록을 console에 찍어봄
		model.addAttribute("list", list); // 내부적으로 request.setAttribute("list", list)임
		return "board/list"; 			// board/list.jsp 으로 전달
	}
	
	// 새글 작성 입력 form
	@GetMapping(value = "insertBoardForm.do")
	public String insertBoardForm() {
		return "board/insert";
	}
	
	// 작성
	@PostMapping(value = "insertBoard.do")
	public void insertBoard(HttpServletRequest request, HttpServletResponse response) {
		// inserBoard 호출
		service.insertBoard(request, response);
	}
	
	/* 게시물 상세 보기 */
	@GetMapping(value = "selectBoardByNo.do")
	public String selectBoardByNo(@RequestParam(value = "no") Long no, Model model) {
		model.addAttribute("board", service.selectBoardByNo(no));
		return "board/detail"; // detail jsp로 forward
	}
	
	/* 게시물 수정 form으로 이동 */
	@GetMapping(value = "updateBoardForm.do")
	public String updateBoardForm(@ModelAttribute(value = "board")Board board) {
		// detail.jsp에서 보낸 파라미터 3개는 Board board가 받고,
		// model에 "board" 속성으로 저장함 : model.addAttribute("board", board)
		return "board/update"; // forward
		// Model의 역할
		// 1. 향상된 request
		// 내부적으로는 setAttribute를 한다.
	}
	
	/* 게시물 수정 */
	@PostMapping(value = "updateBoard.do")
	public void updateBoard(Board board, HttpServletResponse response) {
		// 넘겨 받은 거 보내기
		service.updateBaord(board, response);
	}
	
	/* 게시물 삭제 */
	@GetMapping(value = "deleteBoard.do")	// 번호를 정상적으로 불러오지 못했을 때 기본 값으로 0번을 불러오게 하여 에러메세지를 띄운다.
	public void deleteBoard(@RequestParam(value = "no", required = false, defaultValue = "0") Long no,
						HttpServletResponse response) {
		service.deleteBoard(no, response);
	}
	
		
	
	
}
