package com.koreait.ex11.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex11.domain.Notice;
import com.koreait.ex11.repository.NoticeRepository;

public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// sqlSession으로 부터 Repository를 호출함
	
	/* 목록 */
	@Override
	public List<Notice> selectNoticeList(){
		NoticeRepository repository = sqlSession.getMapper(NoticeRepository.class);
		return repository.selectNoticeList();
	} // NoticeRepository의 selectNoticeList() 호출 -> notice.xml의 id="selectNoticeList" 실행

	/* 상세 보기 */
	@Override
	public Notice selectNoticeByNo(Long no) {
		NoticeRepository repository = sqlSession.getMapper(NoticeRepository.class);
		return repository.selectNoticeByNo(no);
	}

	/* 추가 */
	@Override
	public int insertNotice(Notice notice) {
		NoticeRepository repository = sqlSession.getMapper(NoticeRepository.class);
		return repository.insertNotice(notice);
	}

	/* 수정 */
	@Override
	public int updateNotice(Notice notice) {
		NoticeRepository repository = sqlSession.getMapper(NoticeRepository.class);
		return repository.updateNotice(notice);
	}

	@Override
	public int deleteNotice(Long no) {
		NoticeRepository repository = sqlSession.getMapper(NoticeRepository.class);
		return repository.deleteNotice(no);
	}
	
}
