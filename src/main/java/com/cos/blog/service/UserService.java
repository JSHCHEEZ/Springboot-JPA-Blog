package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@Service 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); //해쉬화
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원정보수정(User user) {
		// 수정시에는 영속성 컨테이너의 user 오브젝트를 영속화시키고, 영속화된 user 오브젝트를 수정
		// select 해서 user오브젝트를 db로부터 가져오는 이유는 영속화를 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 db에 update문 날림
		User persistance = userRepository.findById(user.getId())
				.orElseThrow(() -> {return new IllegalArgumentException("회원 찾기 실패");});
		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); //해쉬화
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됨
		//영속화된 persistance 객체가 변화가 감지되면 더티체킹이 되어 update문을 날려줌
	}
	
//	@Transactional(readOnly = true) //Select 할 때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료 (정합성)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
