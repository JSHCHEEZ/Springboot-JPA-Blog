package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
// 사용자가 요청 -> 응답(Data) 
// 사용자가 요청 -> 응답(HTML 파일)은 @Controller
	
	private static final String Tag = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").email("ssar@test.com").id(10).password("1234").build();
		System.out.println(Tag+"getter " + m.getId());
		m.setId(5000);
		System.out.println(Tag+"getter " + m.getId());
		return "lombokTest 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	//http://localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청: " + m.getId() + ", " + m.getUsername() + ", " +m.getPassword() + ", " +m.getEmail();
	}
	
	//http://localhost:8080/http/post
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)
		return "post 요청: "  + m.getId() + ", " + m.getUsername() + ", " +m.getPassword() + ", " +m.getEmail();
	}
	
	//http://localhost:8080/http/put
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m ) {
		return "put 요청: " + m.getId() + ", " + m.getUsername() + ", " +m.getPassword() + ", " +m.getEmail();
	}
	
	//http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m) {
		return "delete 요청: " + m.getId() + ", " + m.getUsername() + ", " +m.getPassword() + ", " +m.getEmail();
	}
	
}
