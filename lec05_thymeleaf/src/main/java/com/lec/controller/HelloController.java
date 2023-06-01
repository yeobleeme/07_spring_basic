package com.lec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello") // :8080/hello
	public void hello(Model model) {
		model.addAttribute("${greeting}","<h3>Hello Thymeleaf!!!</h3>");
	}
}
