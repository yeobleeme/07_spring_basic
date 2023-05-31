package com.lec.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.domain.Board;

@Controller
public class BoardController {
	
//	@GetMapping("/hello")
//	public String hello() {
//		return "Hello";
//	}
//	
//	@GetMapping("/getBoardList")
//	public String getBoardList() {
//		return "getBoardList";
//	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		
		model.addAttribute("title", "글 목록 조회");
		
		List<Board> boards = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			Board board = new Board();
			board.setSeq(new Long(i));
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스트 작성자 " + i);
			board.setContent(i + "번째 게시글 내용");
			board.setCreateDate(new Date());
			board.setCnt(0l);
			boards.add(board);
		}
		
		
		model.addAttribute("boards", boards);
		
		return "getBoardList";
	}
	

}












