package com.jiwon.Redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jiwon.Redis.model.Member;
import com.jiwon.Redis.model.Role;
import com.jiwon.Redis.repository.MemberRepository;

@SpringBootTest
public class TestMemberRepository {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void MemberTest() {
		Member member = new Member(); 
		member.setMemberName("yoonjiwon"); // 아이디
		member.setPassword(passwordEncoder.encode("admin"));
		member.setEnabled(true);
		member.setRole(Role.ADMIN);
		memberRepository.save(member);
	}
}
