package com.lec.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.exception.BoardNotFoundException;

@Controller
public class EXceptionController {
	
	@RequestMapping("/boardError")
	public String boardError() {
		throw new BoardNotFoundException("no Post Exist");
	}

	@RequestMapping("/illegalArgumentError")
	public String illegalArgumentError() {
		throw new IllegalArgumentException("uncorrect parameter");
	}
	
	
	@RequestMapping("/sqlError")
	public String sqlError() throws SQLException {
		throw new SQLException("wrong SQL phrase");
	}
	
	
	@ExceptionHandler(SQLException.class) 
		public String numberFormatError(SQLException exception, Model model) {
			model.addAttribute("exception", exception);
			return "/errors/sqlError";
		
	}
	
	
	
}




