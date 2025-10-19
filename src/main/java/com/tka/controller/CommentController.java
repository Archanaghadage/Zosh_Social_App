package com.tka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tka.Models.Comment;
import com.tka.Models.User;
import com.tka.Services.CommentService;
import com.tka.Services.UserService;

@RestController
public class CommentController {

	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createCOmment(@RequestBody Comment comment, 
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer postId) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		Comment createdComment=commentService.createComment(comment, postId, user.getId());
				return createdComment;
		
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment( 
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		Comment likedComment=commentService.likedComment( commentId, user.getId());
				return likedComment;
		
	}
}
