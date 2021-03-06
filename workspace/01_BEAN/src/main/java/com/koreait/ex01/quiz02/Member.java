package com.koreait.ex01.quiz02;

import java.util.Set;

public class Member {
	private String name;			  	 // 이름
	private double height;				 // 키
	private double weight;				 // 몸무게
	private BMICalculator bmiCalculator; // 계산기
	private Set<String> course;			 // 과목
	
	/*
	 생성자를 만들 때에는 디폴트 생성자를 함께 만들어야 함
	 그렇지 않으면 xml에서 생성자를 못 찾음 ㅜ
	 */
	
	// 디폴트 생성자 						
	public Member() {
		
	}
	// 생성자
	public Member(String name, double height, double weight, BMICalculator bmiCalculator, Set<String> course) {
		super();
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.bmiCalculator = bmiCalculator;
		this.course = course;
	}
	

	/* getter/ setter */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public BMICalculator getBmiCalculator() {
		return bmiCalculator;
	}
	public void setBmiCalculator(BMICalculator bmiCalculator) {
		this.bmiCalculator = bmiCalculator;
	}
	public Set<String> getCourse() {
		return course;
	}
	public void setCourse(Set<String> course) {
		this.course = course;
	}
	
	public void memberInfo() {
		System.out.println("name : " + name);
		System.out.println("height : " + height + "cm");
		System.out.println("weight " + weight + "kg");
		bmiCalculator.bmiInfo(weight, height);
		System.out.println("course : " + course.toString());
	}
}