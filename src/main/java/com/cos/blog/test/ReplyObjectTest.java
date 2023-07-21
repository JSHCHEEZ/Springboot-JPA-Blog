package com.cos.blog.test;

import com.cos.blog.model.Reply;

public class ReplyObjectTest {
	
	public void toStringTest() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("안녕")
				.build();
		
		System.out.println(reply);
	}

	
}
