package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;


@Service 
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()-> {return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다 id : " + id);});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()-> {return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다 id : " + id);}); //영속화 완료
		System.out.println(requestBoard.getTitle() + "," + requestBoard.getContent() + "@@@@@@@@@@@@@@");
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시 트랜잭션이 종료 됩니다. 이때 더티채킹 -> 자동업데이트 db flush
	}
}