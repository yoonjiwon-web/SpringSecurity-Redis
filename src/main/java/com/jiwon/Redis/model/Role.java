package com.jiwon.Redis.model;

import java.io.Serializable;

// 사용자 권한 정의 테이블모델
public enum Role implements Serializable{
	ADMIN, MANAGER, MEMBER
}
