package com.lec;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lec.domain.Board;
import com.lec.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryMethodTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
//	@BeforeEach
//	public void dataPrepare() {
//		for(int i=1; i<=200; i++) {
//			Board board = new Board();
//			board.setTitle("테스트 제목 " + i);
//			board.setWriter("테스터 " + i);
//			board.setContent("테스트 내용 " + i);
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			boardRepository.save(board);
//		}
//	}
	
//	@Test
//	public void testFindByTitle() {
//		List<Board> boardList = boardRepository.findByTitle("테스트 제목 1");
//		
//		System.out.println("===> 검색 결과");
//		for(Board board:boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
	@Test
	public void testFindByContentContaining() {
		List<Board> boardList = boardRepository.findByContentContaining("17");
		System.out.println("===> 검색 결과");
		for(Board board:boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	

	
	
}






