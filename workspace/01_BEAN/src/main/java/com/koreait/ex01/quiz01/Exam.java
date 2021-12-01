package com.koreait.ex01.quiz01;

import java.util.List;

public class Exam {

	private List<Integer> scores;
	private double avg;
	private char grade;

	// 생성자 생략
	
	public List<Integer> getScores() {
		return scores;
	}


	public void setScores(List<Integer> scores) {	/* <property name="scores"> 태그에 의해서 호출됨. */
		this.scores = scores;
		setAvg(); 	/* 추가한 메소드 실행 */
		setGrade();	/* 추가한 메소드 실행 */
	}


	// 평균 또는 학점 같이 계산이 필요한 경우는 함수를 이용해서
	// 계산 후 사용한다.
	public double getAvg() {
		return avg;
	}


	public void setAvg(double avg) {
		this.avg = avg;
	}

	/* 평균 계산 */
	// scores의 점수를 get해서 계산하라..!
	public void setAvg() {
		int total = 0;
		int length = scores.size();
		for(int i = 0; i < length; i++) {
			total += scores.get(i);			
		}
		this.avg = (double)total / length;
	}
	public char getGrade() {
		return grade;
	}


	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/* 학점 계산 */
	public void setGrade() { /* 추가 */
		if(avg >= 90) grade = 'A';
		else if(avg >= 80) grade = 'B';
		else if(avg >= 70) grade = 'C';
		else if(avg >= 60) grade = 'D';
		else grade = 'F';
	}
}
