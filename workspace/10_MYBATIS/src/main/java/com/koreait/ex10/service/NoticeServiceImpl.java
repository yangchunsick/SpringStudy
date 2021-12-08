package com.koreait.ex10.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex10.domain.Notice;
import com.koreait.ex10.repository.NoticeRepository;

public class NoticeServiceImpl implements NoticeService {

	// 공통적으로 필요한 repository
	// DB를 다녀오는 작업 (?)
	@Autowired
	private NoticeRepository repository;
	
	/* 목록 */
	@Override
	public List<Notice> selectNoticeList() {
		return repository.selectNoticeList();
	}

	/* 추가(1) */
	/*
	@Override
	public void insertNotice(Notice notice, HttpServletResponse response) {
		int result = repository.insertNotice(notice);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('등록 성공')");
				out.println("location.href='/ex10/notice/selectNoticeList'");
				out.println("</script>");
				out.close();
			}else {
				out.println("<script>");
				out.println("alert('등록 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/* 추가(2) */
	@Override
	public void insertNotice(Notice notice, HttpServletResponse response) {
		int result = repository.insertNotice(notice);
		message(result, response, "등록 성공", "등록 실패", "/ex10/notice/selectNoticeList");
	}
	
	/* 상세보기 */
	@Override
	public Notice selectNoticeByNo(Long no) {
		return repository.selectNoticeByNo(no);
	}
	
	/* 수정 */
	@Override
	public void updateNotice(Notice notice, HttpServletResponse response) {
		int result = repository.updateNotice(notice);
		message(result, response, "수정 성공", "수정 실패", "/ex10/notice/selectNoticeByNo?no=" + notice.getNo());
	}
	
	/* 삭제 */
	@Override
	public void deleteNotice(Long no, HttpServletResponse response) {
		int result = repository.deleteNotice(no);
		message(result, response, "삭제 성공", "삭제 실패", "/ex10/notice/selectNoticeList");
	}

}