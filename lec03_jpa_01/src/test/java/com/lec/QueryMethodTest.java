package com.lec;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
//	@Test
//	public void testFindByContentContaining() {
//		List<Board> boardList = boardRepository.findByContentContaining("17");
//		System.out.println("===> 검색 결과");
//		for(Board board:boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
//	@Test
//	public void testFindByTitleContainingOrContentContaining() {
//		List<Board> boardList = boardRepository.findByTitleContainingOrContentContaining("두", "17");
//		System.out.println("===> 검색 결과");
//		for(Board board:boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
//	@Test
//	public void testFindByTitleContainingOrderBySeqDesc() {
//		List<Board> boardList = boardRepository.findByTitleContainingOrderBySeqDesc("17");
//		System.out.println("===> 검색 결과");
//		for(Board board:boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
//	@Test
//	public void testFindByTitleContaining() {
//		
//		// Pageable paging = PageRequest.of(0, 5);  // limit 0, 5;
//		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");  // order by seq desc limit 0, 5;
//		
//		List<Board> boardList = boardRepository.findByTitleContaining("제목", paging);
//		System.out.println("===> 검색 결과");
//		for(Board board:boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
	@Test
	public void testFindByTitleContaining() {
		
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");  // order by seq desc limit 0, 5;
		Page<Board> pageInfo = boardRepository.findByTitleContaining("제목", paging);
		
		System.out.println("페이지 사이즈 = " + pageInfo.getSize());
		System.out.println("총 페이지 수 = " + pageInfo.getTotalPages());
		System.out.println("총 레코드 수 = " + pageInfo.getTotalElements());
		System.out.println("다음 페이지 유무 = " + pageInfo.nextPageable());
		
		List<Board> boardList = pageInfo.getContent();
		
		System.out.println("===> 검색 결과");
		for(Board board:boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	

	
	
}






