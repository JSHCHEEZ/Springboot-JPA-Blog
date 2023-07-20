package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//자동으로 bean 등록이 된다.
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
	//JPA 네이밍 전략
	// SELECT * From user Where username = ?1 And password = ?2 
	//User findByUsernameAndPassword(String username, String password);
	
//	@Query(value = "SELECT * From user Where username = ?1 And password = ?2", nativeQuery = true)
//	User login(String username, String password);
}