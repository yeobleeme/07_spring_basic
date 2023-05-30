package com.lec.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lec.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	List<Board> findByTitle(String searchWord);

	List<Board> findByContentContaining(String string);

	
	

}
