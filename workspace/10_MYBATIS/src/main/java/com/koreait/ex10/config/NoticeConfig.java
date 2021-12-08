package com.koreait.ex10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.ex10.repository.NoticeRepository;
import com.koreait.ex10.service.NoticeService;
import com.koreait.ex10.service.NoticeServiceImpl;

@Configuration
public class NoticeConfig {
	
	
	// Configuration Bean으로 등록을 해야
	// AutoWired로 사용할 수 있다.
	@Bean
	public NoticeRepository repository() {
		return new NoticeRepository();
	}
	
	@Bean
	public NoticeService service() {
		return new NoticeServiceImpl();
	}
	
}
