package com.jiwon.Redis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jiwon.Redis.model.Member;
 
//테이블의 pk는 integer형이라고 정의
public interface MemberRepository extends JpaRepository<Member, Integer> { 
	@Query(value = "SELECT * FROM member WHERE memberName = ?", nativeQuery = true)
	Optional<Member> findByUsername(String memberName);
}
