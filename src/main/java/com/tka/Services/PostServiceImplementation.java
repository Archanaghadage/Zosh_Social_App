package com.tka.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.Models.Post;
import com.tka.Models.User;
import com.tka.Repository.PostRepository;
import com.tka.Repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {

		User user = userService.findUserById(userId);
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(java.time.LocalDate.now()); 

		newPost.setUser(user);

		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (post.getUser().getId() != user.getId()) {
			throw new Exception("You cannot delete another user post");
		}
		postRepository.delete(post);
		return "Delete Post Successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {

		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> otp = postRepository.findById(postId);
		if (otp.isEmpty()) {
			throw new Exception("Post Not Found with id: " + postId);
		}
		return otp.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else user.getSavedPost().add(post);
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
	    Post post = findPostById(postId);
	    User user = userService.findUserById(userId);

	    if (post.getLiked().contains(user)) {
	        post.getLiked().remove(user); // 👎 unlike
	    } else {
	        post.getLiked().add(user);    // 👍 like
	    }

	    return postRepository.save(post);
	}

}
