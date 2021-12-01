package com.koreait.ex01.java.quiz02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);

		System.out.println("*** *** *** *** *** *** ***");
		
		Soldier s1 = ctx.getBean("soldier1", Soldier.class);
		s1.soldierInfo();
		
		System.out.println("*** *** *** *** *** *** ***");
		
		Soldier s2 = ctx.getBean("soldier2", Soldier.class);
		s2.soldierInfo();
		 
		System.out.println("*** *** *** *** *** *** ***");
		ctx.close();
		
	}

}
