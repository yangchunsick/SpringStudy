package com.koreait.ex06.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex06.domain.Board;
import com.koreait.ex06.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {
	
	// 공통적으로 필요한 것은 ?
	// 1. Bord레파지토리(저장소)가 필요함
	
	// 필드로 스프링 개입 없이
	// Configlation을 뒤져서 타입이 일치한 @Bean을 불러옴
	// 여기서 타입은 BoardRepository이 타입이 되는 거임 BeanConfig가보면 있음
	@Autowired
	private BoardRepository repository;
	
	/* 게시물 리스트 뽑기 */
	@Override
	public List<Board> selectBoardList() {
		return repository.selectBoardList();
	}
	
	/* 게시물 추가하기 */
	@Override
	public void insertBoard(HttpServletRequest request, HttpServletResponse response) {
		// 받은 Param 꺼내기
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// writer title content를 한 곳으로 모으기
		Board board = new Board(); 
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		
		// DB 다녀오기
		int result = repository.insertBoard(board);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('게시글 등록 성공')");
				out.println("location.href='/ex06/board/selectBoardByNo.do?no=" + board.getNo() + "'");
				out.println("</script>");
				out.close();
			}else {
				out.println("<script>");
				out.println("alert('게시글 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/*게시물 상세 보기 */
	@Override
	public Board selectBoardByNo(Long no) {
		return repository.selectBoardByNo(no);
	}
	
	/* 게시물 수정 */
	@Override
	public void updateBaord(Board board, HttpServletResponse response) {
		int result = repository.updateBoard(board);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('게시글 수정 성공')");
				out.println("location.href='/ex06/board/selectBoardList.do'");
				out.println("</script>");
				out.close();
			}else {
				out.println("<script>");
				out.println("alert('게시글 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 게시물 삭제 */
	@Override
	public void deleteBoard(Long no, HttpServletResponse response) {
		int result = repository.deleteBoard(no);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('게시글 삭제 성공')");
				out.println("location.href='/ex06/board/selectBoardList.do'");
				out.println("</script>");
				out.close();
			}else {
				out.println("<script>");
				out.println("alert('게시글 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}