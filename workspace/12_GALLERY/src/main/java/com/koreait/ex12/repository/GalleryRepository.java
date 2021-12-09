package com.koreait.ex12.repository;

import java.util.List;

import com.koreait.ex12.domain.Gallery;

public interface GalleryRepository {

	// GalleryRepository는 gallery.xml과 곧 바로 연결되어 있다 !!
	// method의 이름은 xml에 있는 id와 동일 해야한다.
	// 반환 타입 = resultType
	// 매개변수 = parameterType
	
	// 목록
	public List<Gallery> selectGalleryList();
	
	// 상세 보기
	public Gallery selectGalleryByNo(Long no);
	
	// 추가
	public int insertGallery(Gallery gallery);

	// 수정
	public int updateGallery(Gallery gallery);
	
	// 삭제 
	public int deleteGalery(Long no);
	
	
}
