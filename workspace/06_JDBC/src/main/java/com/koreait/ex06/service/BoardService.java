package com.koreait.ex06.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.ex06.domain.Board;

public interface BoardService {
	//
	public List<Board> selectBoardList();
	//
	public void insertBoard(HttpServletRequest request, HttpServletResponse response);
	// 반환 타입이 Board
	public Board selectBoardByNo(Long no);
	// 수정
	public void updateBaord(Board board, HttpServletResponse response);
	// 삭제
	public void deleteBoard(Long no, HttpServletResponse response);
}