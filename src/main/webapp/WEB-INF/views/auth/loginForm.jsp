<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">
	<!-- 스프링 시큐리티 로그인 방식 적용. action, method 선언 
		/auth/loginProc 으로 들어온 로그인 요청은, SecurityConfig.java 로 던진다!!
		그래서 <button> 태그도 <form> 안에 설정함. js도 안태움
	-->
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
				
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group">
			<label class="form-check-label">
				<input name="remember" class="form-check-input" type="checkbox">Remember me~~~
			</label>
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form> 
</div>