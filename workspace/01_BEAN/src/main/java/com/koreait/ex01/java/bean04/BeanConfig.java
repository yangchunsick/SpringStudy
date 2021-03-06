package com.koreait.ex01.java.bean04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("app-context05.xml")	// 여기 있는 bean을 가져오세요.
@Configuration
public class BeanConfig{
	
	@Bean
	public Publisher publisher1() {
		return new Publisher("서울출판사", "02-000-0000");
	}
	
	@Bean
	public Book book1() {
		return new Book("JSP바로 잡기", "김박사", publisher1());
	}

}
