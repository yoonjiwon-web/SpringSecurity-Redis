package com.jiwon.Redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	// 로그인 form 페이지
	@RequestMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	@RequestMapping("/auth/main")
	public String main() {
		return "auth/main";
	}
	
	@RequestMapping("/auth/admin")
	public String admin() {
		return "auth/admin";
	}
	
	@RequestMapping("/auth/member")
	public String member() {
		return "auth/member";
	}
	
	@RequestMapping("/auth/manager")
	public String manager() {
		return "auth/admin";
	}
	
	@RequestMapping("/auth/accessDeny")
	public String accessDeny() {
		return "auth/accessDeny";
	}
}
