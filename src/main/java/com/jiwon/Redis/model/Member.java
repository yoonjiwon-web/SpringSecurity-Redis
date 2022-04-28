package com.jiwon.Redis.model;
  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 100, unique = true) 
	private String memberName; // 아이디

	@Column(nullable = false, length = 100) // 123456 => 해쉬 (비밀번호 암호화)
	private String password; 

	// DB는 Role 테이블이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
 
	 
}
