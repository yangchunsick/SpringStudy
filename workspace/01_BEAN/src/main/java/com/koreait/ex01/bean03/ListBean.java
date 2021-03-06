package com.koreait.ex01.bean03;

import java.util.List;

public class ListBean {

	// field
	private List<String> list; // java.util

	// construct
	// 디폴트 생성자 사용
	
	// getter / setter
	public List<String> getList() {
		return list;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}
	public void listInfo() {
		// null값을 피하기 위한 초기화 값
		for(int i = 0, length = list.size(); i < length; i++) {
			System.out.println((i + 1) + "번 째 요소: " + list.get(i));
		}
	}
}
