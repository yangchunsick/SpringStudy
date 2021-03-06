package com.koreait.ex01.bean03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainCalss {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context03.xml");

		System.out.println("**********");
		
		ListBean listBean = ctx.getBean("listBean", ListBean.class);
		listBean.listInfo();
		
		System.out.println("**********");
		
		SetBean setBean = ctx.getBean("setBean", SetBean.class);
		setBean.setInfo();
		
		System.out.println("**********");
		
		MapBean mapBean = ctx.getBean("mapBean", MapBean.class);
		mapBean.mapInfo();

		System.out.println("**********");
	}

}
