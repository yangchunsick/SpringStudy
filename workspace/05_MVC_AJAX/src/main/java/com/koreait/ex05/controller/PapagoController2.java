package com.koreait.ex05.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PapagoController2 {
	
	@GetMapping("openPapago2.do")	// index.jsp에서 openPapago2.do 맵핑을타고
	public String openPapago() {	// 넘어가는 메소드
		return "papago2";			// papago2.jsp를 반환 return 한다.
	}
	
	@PostMapping(value="papago2.do",
				 produces = "application/json; charset=UTF-8")
	public String papago(@RequestBody Map<String, String> param, HttpServletResponse response) {
		
		String clientId = "bi0zFUfar9qogHzkL2G2";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "DTN7EPOmAv";//애플리케이션 클라이언트 시크릿값";
        
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";// Papago 번역 API URL
        
        // papago2.jsp에서 입력 받은 param들 꺼내기
        String source = param.get("source");
        String target = param.get("target");
        String text = param.get("text");
        
        try {
        	text = URLEncoder.encode(text, "UTF-8");
        }catch (UnsupportedEncodingException e) {
        	throw new RuntimeException("인코딩 실패", e);
        }
        
        Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        String result = null;
        
	}

}
