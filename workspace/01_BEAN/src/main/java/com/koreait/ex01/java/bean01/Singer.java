package com.koreait.ex01.java.bean01;

public class Singer {
	
	private String name;
	private Song song;
	
	
	// 디폴트 생성자
	public Singer() {
		
	}
	
	public Singer(String name, Song song) {
		super();
		this.name = name;
		this.song = song;
	}

	// getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}
}
