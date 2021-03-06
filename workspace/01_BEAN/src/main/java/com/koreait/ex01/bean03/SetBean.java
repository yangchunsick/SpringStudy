package com.koreait.ex01.bean03;

import java.util.Set;

public class SetBean {
	
	// field
	private Set<String> set; // java.util

	// constructor
	// 디폴트 생성자

	// getter / setter
	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}
	public void setInfo() {
		// Set은 인덱스가 없다 (순서가 없는 자료형)
		// 중복을 회피 할 수 있음
		// str에 저장되어 있는 걸 하나씩 빼 주는 것임
		for(String str : set) {
			if(str != null) {
				System.out.println(str);
			}
		}
	}
}
