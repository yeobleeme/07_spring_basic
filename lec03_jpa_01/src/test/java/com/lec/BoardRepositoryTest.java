package com.lec;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lec.domain.Board;
import com.lec.persistence.BoardRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired  // new BoardRepository() 수동생성 대신 자동생성 후 주입
	public BoardRepository boardRepo; 
	
//	@Test
//	public void testInsertBoard() {
//		
//		Board board = new Board();
//		board.setTitle("첫 번째 게시글");
//		board.setWriter("테스터");
//		board.setContent("동작 확인");
//		board.setCreateDate(new Date());
//		board.setCnt(1L);
//		boardRepo.save(board);  // insert into board
//		
//	}
	
//	@Test
//	public void testGetBoard() {
//		
//		Board board = boardRepo.findById(2L).get();
//		System.out.println(board);
//	}
	
//	@Test
//	public void testUpdateBoard() {
//		
//		System.out.println("=== 2번 글 조회 ===");
//		Board board = boardRepo.findById(2L).get();
//		
//		System.out.println("=== 2번 글 수정 ===");
//		board.setTitle("제목 수정");
//		boardRepo.save(board);
//		System.out.println(boardRepo.findById(2L).get());
//		
//	}
	
//	@Test
//	public void testDeleteBoard() {
//		
//		boardRepo.deleteById(2L);
//		
//	}
	
	@Test
	public void testGetBoardList() {
		
		System.out.println(boardRepo.findAll().iterator().toString());
		Iterable<Board> boardList = boardRepo.findAll();
				
		for(Board board:boardList) {
			System.out.println(board);
		} 

	}
	
	
	
	

}











