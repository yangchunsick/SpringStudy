package com.koreait.ex01.bean01;

import org.springframework.context.support.AbstractApplicationContext;		// Spring Class임
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		// GenericXmlApplicationContext 클래스	-- 기존 자바의 Class 명을 피해 만들다 보니 이름이 길다.
		
		// 1. spring bean configuration file이 만든 <bean>을 생성하는 스프링 클래스	** SpringClass임 주의 JavaClass아님 주의
		// 2. AbstractApplicationContext 클래스의 자식 클래스
		
		String resourceLocations = "classpath:app-context01.xml";		// classpath: 생락 가능  -- file 명 지정
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		// 스프링은 app-context1.xml에 만들어 둔 <bean> 태그를 모두 bean으로 생성해서 가지고 있음.
		// getBean() 메소드를 이용해서 생성된 bean을 가져옴.
		
		// 제어의 역전
		// IoC : Inversion of Control 
		// 기존에는 개발자가 new를 이용해서 직접 객체를 생성 했지만
		// 스프링은 스프링이 객체를 생성하고 개발자는 가져다 사용한다는 의미
		
		System.out.println("---e1---");
		EngineerCalculator e1 = ctx.getBean("eCalculator1", EngineerCalculator.class);	// class는 .class라고 지정해준다.
		e1.add();
		e1.sub();
		e1.multiply();
		e1.div();
		e1.mod();
		System.out.println("---e1---");
		System.out.println("---  ---");
		System.out.println("---e2---");
											// 이름			// 클래스 이름
		EngineerCalculator e2 = ctx.getBean("eCalculator2", EngineerCalculator.class);
		e2.add();
		e2.sub();
		e2.multiply();
		e2.div();
		e2.mod();
		System.out.println("---e2---");
		System.out.println("---  ---");		
		System.out.println("---e3---");
		EngineerCalculator e3 = ctx.getBean("eCalculator3", EngineerCalculator.class);
		e3.add();
		e3.sub();
		e3.multiply();
		e3.div();
		e3.mod();
		System.out.println("---e3---");
		System.out.println("---  ---");
		System.out.println("---e4---");
		EngineerCalculator e4 = ctx.getBean("eCalculator4", EngineerCalculator.class);
		e4.add();
		e4.sub();
		e4.multiply();
		e4.div();
		e4.mod();
		System.out.println("---e4---");
		
		ctx.close(); // 닫아줄 것 
	}

}