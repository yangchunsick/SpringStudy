package com.koreait.ex01.java.quiz01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	@Bean(name="calc") // 여기서도 bean의 이름을 줄 수 있어요
	public Calculator abc() {	// 그럼 메소드 이름은 안 써요
		return new Calculator();
	}	
	@Bean
	public Gugudan gugudan() {
		Gugudan g = new Gugudan();
		g.setBegin(1);
		g.setEnd(9);
		g.setCalculator(abc());
		return g;
	}	
}