package com.tka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tka.Models.User;
import com.tka.Repository.UserRepository;
import com.tka.Services.UserServiceImplementation;
import com.tka.config.JwtProvider;

@RestController
public class UserController {

	private final HomeController homeController;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserServiceImplementation userService;

	UserController(HomeController homeController) {
		this.homeController = homeController;
	}

	@GetMapping("/api/users")
	public List<User> getUsers() {

		List<User> users = userRepository.findAll();
		return users;
	}

	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String jwt,
	                       @RequestBody User user) throws Exception {
	    User reqUser = userService.findUserByJwt(jwt);
	    return userService.updateUser(user, reqUser.getId());
	}


	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception {
		
	 User reqUser=userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}

	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		List<User> user = userService.searchUser(query);
		return user;
	}
	
//	@DeleteMapping("users/{userId}")
//	public String seleteUser(@PathVariable Integer userId) throws Exception {
//		Optional<User> user = userRepository.findById(userId);
//		if (user.isEmpty()) {
//			throw new Exception("user not exist with id " + userId);
//		}
//		userRepository.delete(user.get());
//		return "User Deleted Successfully" + userId;

	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);

		return user;
	}

	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		return userService.findUserById(id);

	}

//	}
}
