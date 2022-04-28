package com.jiwon.Redis.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jiwon.Redis.model.Member; 

import lombok.Data;

@Data
public class PrincipalDetail implements UserDetails{

	@Autowired
	private Member member;
	
	public PrincipalDetail(Member member) {
		this.member = member;
	}
	 
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member.getMemberName();
	}

	// 계정이 만료되었는지 return. true 여야지 만료안됨
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 잠겼는지 return. true 여야지 안잠김
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비번 만료되었는지 retrun, true 여야지 안만료
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 활성화. true 여야지 사용가능
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		// 람다식으로 표현
		collectors.add(()->{return "ROLE_" + member.getRole();}); // 반드시 ROLE_ 을 앞에 붙여서, ROLE_USER로 리턴해야됨.
		return collectors;
	}

	
}