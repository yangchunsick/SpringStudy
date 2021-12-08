package com.koreait.ex10.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex10.domain.Notice;

public class NoticeRepository {

	// Bean의 형태를 보고 만든다.
	// private 반환타입 메소드이름;
	@Autowired
	 private SqlSessionTemplate sqlSession;
	
	// 목록
	public List<Notice> selectNoticeList(){
		return sqlSession.selectList("mapper.notice.selectNoticeList");
	}
	
	// 상세보기
	public Notice selectNoticeByNo(Long no) {
		return sqlSession.selectOne("mapper.notice.selectNoticeByNo", no);
	}
	
	// 추가
	public int insertNotice(Notice notice) {
		return sqlSession.insert("mapper.notice.insertNotice", notice);
	}
	
	// 수정
	public int updateNotice(Notice notice) {
		return sqlSession.update("mapper.notice.updateNotice", notice);
	}
	
	// 삭제
	public int deleteNotice(Long no) {
		return sqlSession.delete("mapper.notice.deleteNotice", no);
	}
	
}
