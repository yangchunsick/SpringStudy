package com.koreait.ex05.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.ex05.domain.Board;

@RestController // ajax만 처리하는 Controller @ResponseBody를 생략할 수 있음
@RequestMapping("board")
public class BoardController {

	@GetMapping(value="v1.do",
				produces = "application/json; charset=utf-8")
	public Board v1(Board board) {
		return board;
	}
	
	@PostMapping(value="v2.do",
				produces = "application/json; charset=utf-8")
	public Board v2(@RequestBody Board board) {
		return board;
	}
	
	@PostMapping(value="v3.do",
				 produces = "application/json; charset=utf-8")
	public Map<String, Object> v3 (@RequestBody Map<String, Object> map){
		return map;
	}
	
	
}
