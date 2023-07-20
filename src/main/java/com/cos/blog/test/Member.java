package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@Data //@Getter + @Setter
//@AllArgsConstructor  // 모든 변수 설정하는 생성자
@NoArgsConstructor // 빈 생성자
public class Member {
	private  int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
}
