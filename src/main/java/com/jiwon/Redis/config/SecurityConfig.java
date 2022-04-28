package com.jiwon.Redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
 

/* 스프링 시큐리티 쓰려면 아래 3가지 어노테이션은 필수로 셋트!! */
@Configuration  // 빈등록 (IOC 관리)
@EnableWebSecurity // 스프링 시큐리티 필터 추가. 아래 설정을 이 class 파일에서 하겠다!! 
@EnableGlobalMethodSecurity(prePostEnabled = true ) // 특정주소로 접근하면 권한/인증을 미리 체크하겠다.
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	   
	@Autowired
	private PrincipalDetailService principalDetailService;
	 
	@Bean // IOC
	public BCryptPasswordEncoder encodePWD() {
		return  new BCryptPasswordEncoder();
	}
	 
	// 스프링 시큐리티가 password를 어떤 해쉬방법으로 인코딩했는지 알아야지,
	// 같은 해쉬로 암호화해서 db에 있는 해쉬값과 비교해서 가져올수있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println("AuthenticationManagerBuilder 들어옴....");
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD()); // username 을 받아왔다면, encodePWD()로 password 해쉬값 비교!!\
		System.out.println("AuthenticationManagerBuilder 완료됨....");
	}
	
	
	// 로그인요청이 오는 순간 먼저 실행된다.
	@Override
	protected void configure(HttpSecurity security) throws Exception { 
			
			security
			.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음. 나중에 배포 시에는 csrf 토큰 받고나서, 없애기!!)
			.authorizeHttpRequests()
			.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // auth 경로로 들어오는 건, 누구나 허용한다!!
			.permitAll()
			.anyRequest() // 그외에는 모두 인증이 되어야되~~
			.authenticated() // 스프링 시큐리티에 의해서 로그인되었다? -> 그럼 접근가능하지!
	    .and()
	    	.formLogin()
	    	.loginPage("/auth/loginForm") // 인증이 필요한 경우에는 요 url로 이동시켜~~
	    	// jsp로부터 아래 url로 로그인 요청이 오면 스프링 시큐리티로 보내~~~ (pricipalDetailService)
	    	.loginProcessingUrl("/auth/loginProc") // jsp에서 action으로 선언한 url를 Controller가 아닌, 스프링 시큐리티가 대신 가로채서 수행!!
	    	.defaultSuccessUrl("/"); // 성공시키면 / 로 다시 이동 
			
		// configure(AuthenticationManagerBuilder auth) 요기로 가서 패스워드 암호화해서 service로 이동시킴.
	} 
	/*
		security.authorizeHttpRequests().antMatchers("/").permitAll(); // 모두 다 접근 가능!
		security.authorizeHttpRequests().antMatchers("/auth/**","/member/**","").authenticated(); // main 패턴 스프링 시큐리티에 의해 로그인되었나? 그럼 접근가능하지!!
		security.authorizeHttpRequests().antMatchers("/manager/**").hasAnyAuthority("MANAGER", "ADMIN"); // manager/** 패턴은 MANAGER, ADMIN 만 접근가능!!
		security.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN"); // admin/** 패턴은 ADMIN 만 접근가능!!
		 
		security.formLogin().loginPage("/auth/loginForm").defaultSuccessUrl("/auth/main", true); // 로그인 페이지, 그리고 성공했을때 어디로 넘길지
		security.formLogin().loginProcessingUrl("/loginAction").defaultSuccessUrl("/auth/main",true); // 로그인 액션 프로세스. (post)
		security.exceptionHandling().accessDeniedPage("/auth/accessDeny");
		security.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/"); // 로그아웃
    */
	}