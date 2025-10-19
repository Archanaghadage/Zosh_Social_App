package com.tka.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.Models.Comment;
import com.tka.Models.Post;
import com.tka.Models.User;
import com.tka.Repository.CommentRepository;
import com.tka.Repository.PostRepository;

@Service
public class CoomentServiceImpl implements CommentService{


	@Autowired
	PostService postService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public Comment createComment(Comment comment, 
			Integer postId, 
			Integer userId) throws Exception {
		
		User user=userService.findUserById(userId);
		
		Post post =postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment savedCommnet=commentRepository.save(comment);
		
	    post.getComments().add(savedCommnet);
	    
	    postRepository.save(post);
	    
		return savedCommnet;
	}

	@Override
	public Comment findCommentById(Integer commnetId) throws Exception {
		Optional<Comment> opt=commentRepository.findById(commnetId);
		if(opt.isEmpty()) {
			throw new Exception ("Comment not exists");
		}
		return opt.get();
	}

	@Override
	public Comment likedComment(Integer commentId, Integer userId) throws Exception {
		Comment comment=findCommentById(commentId);
		User user=userService.findUserById(userId);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else {
			comment.getLiked().remove(user);
		}
		
		return commentRepository.save(comment);
	}

}
