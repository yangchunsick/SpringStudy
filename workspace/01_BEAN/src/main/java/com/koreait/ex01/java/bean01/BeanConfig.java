package com.koreait.ex01.java.bean01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 
	@Configuration
	안녕 난 Bean을 만드는 Java 클래스야 !
	날 이용하려면 cglib 디펜던시를 추가 해야 돼
*/
@Configuration
public class BeanConfig {
	
	// 메소드 1개 = Bean 1개
	// Bean을 만드는 메소드는 @Bean 에너테이션이 필요함.
	
	// 반환타입 : Song <bean class="Song">
	// 메소드 이름 : mySong <bean id="mySong">
	
	@Bean	   // ↓ 이놈이 Bean이래
	public Song mySong() {
		// setter injection을 또는 constructor injection을 하던 자유..!!
		
		Song result = new Song();
		result.setTitle("hello"); // <property name="title" value="hello">
		result.setGenre("balad"); // <property name="genre" value="balad">
		return result;
	}
	
	@Bean
	public Singer mySinger() { // <bean class="Singer" id="mySinger">
		// return new Singer(name, song)	<constructor-arg>
		return new Singer("adele", mySong()); 
	}

}
