package com.koreait.ex01.java.bean01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		// 1. AnnotationConfigApplicationContext 클래스 // 에너테이션 컨피그 어플리케이션 컨텍스트
		// 2. AbstractApplicationContext 클래스의 자식 클래스
		// @Configuration과 @Bean을 이용한 Bean 생성
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
		
		Singer singer = ctx.getBean("mySinger", Singer.class); // (아이디, class)
		System.out.println(singer.getName());
		System.out.println(singer.getSong().getTitle());
		System.out.println(singer.getSong().getGenre());
			
		ctx.close();
		
	}

}
