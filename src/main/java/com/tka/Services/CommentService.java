package com.tka.Services;

import com.tka.Models.Comment;

public interface CommentService {

	public Comment createComment(
			Comment comment, 
			Integer postId, 
			Integer userId) throws Exception; 
	
	public Comment findCommentById(Integer commnetId) throws Exception;
	
	public Comment likedComment(
			Integer commentId, 
			Integer userId) throws Exception;
}
