package com.koreait.ex01.quiz03;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnector {
	/*
	 	오라클 DB 서버에 접속
	 	JDBC JAR가 있어야 함
	 */
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	/* 생성자 */
	public OracleConnector() {
		/* 디폴트 생성자 */
	}

	public OracleConnector(String driverClassName, String url, String username, String password) {
		super();
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	/* getter / setter */
	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Connection getConnection(){	/* 추가 */
		Connection con = null;
		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("[" + username + "]"+ "계정에 접속 되었습니다.");
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}

}
