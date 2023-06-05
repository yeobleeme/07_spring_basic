package com.lec.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.Board;
import com.lec.persistence.BoardRepository;
import com.lec.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public long getTotalRowCount(Board board) {
		
		return boardRepository.count();
	}

	@Override
	public Board getBoard(Board board) {
		
		Optional<Board> findBoard = boardRepository.findById(board.getSeq());
		if(findBoard.isPresent())
			return findBoard.get();
		else return null;
	}

	@Override
	public Page<Board> getBoardList(Pageable pageable, String searchType, String searchWord) {

		if(searchType.equalsIgnoreCase("title")) {
			return boardRepository.findByTitleContaining(searchWord, pageable);
		} else if(searchType.equalsIgnoreCase("writer")) {
			return boardRepository.findByWriterContaining(searchWord, pageable);
		} else {
			return boardRepository.findByContentContaining(searchWord, pageable);
		}
	}

	@Override
	public void insertBoard(Board board) {
		
		boardRepository.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		
		Board findBoard = boardRepository.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepository.save(board);
	}

	@Override
	public void deleteBoard(Board board) {
		
		boardRepository.deleteById(board.getSeq());
	}

}
