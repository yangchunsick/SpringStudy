package com.koreait.ex01.quiz01;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainCalss {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("quiz01.xml");
		
		Student student = ctx.getBean("student", Student.class);
		System.out.println("이름 :" + student.getName());
		System.out.println("나이 :" + student.getAge());
		System.out.println("점수 :" + student.getExam().getScores().toString());
		System.out.println("평균 :" + student.getExam().getAvg());
		System.out.println("학점 :" + student.getExam().getGrade());
		for(Map.Entry<String, String> entry : student.getHome().entrySet()){
			System.out.println("[기타 정보]" + entry.getKey() + ":" + entry.getValue());
		}
		ctx.close();
	}

}
