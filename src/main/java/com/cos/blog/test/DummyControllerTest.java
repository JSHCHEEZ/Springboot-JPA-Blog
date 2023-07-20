package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

//html이 아니라 data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다. id : " + id;
		}
		
		return "삭제되었습니다. id : " + id;
	}
	
	//save함수는 id를 전달하지 않으면 insert
	//save함수는 id를 전달하고 해당 id가 존재하면 update
	//save함수는 id를 전달하고 해당 id가 존재하지 않으면 insert
	//email, password
	@Transactional  //함수 종류시 자동 commit 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { 
		//JSON 데이터를 요청 => JavaObject(MessageConverter)
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		// 영속화
		User user = userRepository.findById(id).orElseThrow(() ->{
			return new IllegalArgumentException("해당 유저는 없습니다. id:" + id);
		}); 
		
		// user 오브젝트의 값의 변경 => update 수행 됨
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		//더티 체킹 (쌓아둔 후 한번에 커밋...)
		
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	//한페이지당 2건의 데이터를 리턴받음
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
			Page<User> PagingUser = userRepository.findAll(pageable);
			
			List<User> users = PagingUser.getContent();
			
			return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id:" + id);
			}
		});
		
		// 요청 : 웹브라우저 , 반환 : 자바오브젝트
		// 웹브라우저가 이해할 수 있도록 변환이 필요한 -> JSON
		// 스프링부트는 MessageConverter가 응답시에 자동 번역(Jackson 라이브러리 사용)
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
}
