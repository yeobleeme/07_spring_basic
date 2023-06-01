package com.lec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.domain.Member;
import com.lec.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberReposotory;
	
	@Override
	public Member getMember(Member member) {
		Optional<Member> findMember = memberReposotory.findById(member.getId());
		if(findMember.isPresent()) 
			return findMember.get();	
		else return null;
	}

}












