package com.lec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.domain.Member;
import com.lec.service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/insertMember")
	public String insertBoardView(@ModelAttribute("member") Member member) {
			
		return "insertMember";
	}
	
	@PostMapping("/insertMember")
	public String insertBoard(@ModelAttribute("member") Member member) {	
		
		memberService.insertMember(member);
		return "redirect:getMemberList";
	}
	
	@RequestMapping("/getMemberList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model) {
		
		List<Member> memberList = memberService.getMemberList(member);
		model.addAttribute("memberList", memberList);
		return "getMemberList";
	}

}










