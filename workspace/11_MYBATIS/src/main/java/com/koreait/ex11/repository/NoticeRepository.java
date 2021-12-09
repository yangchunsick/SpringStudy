package com.koreait.ex11.repository;

import java.util.List;

import com.koreait.ex11.domain.Notice;

public interface NoticeRepository {
	
	/* mapper XML을 참고해서 작성 */
	/* 메소드의 이름을 mapper id로 사용한다..! */
	/* notice.xml와 곧바로 연결이 된다. */
	public List<Notice> selectNoticeList();
	
	public Notice selectNoticeByNo(Long no);
	
	public int insertNotice(Notice notice);
	
	public int updateNotice(Notice notice);
	
	public int deleteNotice(Long no);
	
}
