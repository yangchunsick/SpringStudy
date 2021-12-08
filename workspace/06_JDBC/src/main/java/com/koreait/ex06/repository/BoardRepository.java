package com.koreait.ex06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.koreait.ex06.domain.Board;

public class BoardRepository {

	/* singleton으로 DataSource를 찾는 */
	private DataSource dataSource;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private static BoardRepository instance;
	
	public BoardRepository() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");	// context.xml에 있는 jdbc/oracle를 찾는 작업
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectBoardList(){
		List<Board> list = new ArrayList<Board>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, CREATED, LASTMODIFIED FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Board board = new Board(rs.getLong(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6));
										list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	public int insertBoard(Board board) {
		int result = 0; // result를 초기화
		try {
			con = dataSource.getConnection(); // 데이터 소스에서 얻어 옴
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))"; //쿼리문 작성
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContent());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	// 매개변수 Long타입의 no전달
	public Board selectBoardByNo(Long no){
		Board board = null; // 일단 초기화
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, CREATED, LASTMODIFIED FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();	// 실행 코드
			if (rs.next()) {	// if로 있다 없다 판단
				board = new Board(rs.getLong(1),	// board 만들기	// board no 불러오기
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		// 반환 해주는 것은 board
		return board;
	}
	
	/* 게시물 수정 */
	public int updateBoard(Board board) {
		int result = 0; // result를 초기화
		try {
			con = dataSource.getConnection(); // 데이터 소스에서 얻어 옴
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, LASTMODIFIED = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setLong(3, board.getNo());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	/* 게시물 삭제 */
	public int deleteBoard(Long no) {
		int result = 0; // result를 초기화
		try {
			con = dataSource.getConnection(); // 데이터 소스에서 얻어 옴
			sql = "DELETE FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
