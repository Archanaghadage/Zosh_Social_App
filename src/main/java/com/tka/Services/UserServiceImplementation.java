package com.tka.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.Models.User;
import com.tka.Repository.UserRepository;
import com.tka.config.JwtProvider;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());

		User savedUser = userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		throw new Exception("User not exist with userId " + userId);

	}

	@Override
	public User findUserByEmail(String email) {
		User user =userRepository.findByEmail(email);
		return user;
	}

//	@Override
//	public User followUser(Integer reqUserId, Integer userId2) throws Exception {
//	    User reqUser = findUserById(reqUserId);
//	    User user2 = findUserById(userId2);
//
//	    // Prevent self-following
//	    if (reqUser.getId().equals(user2.getId())) {
//	        throw new Exception("You cannot follow yourself.");
//	    }

//	    // Check if already following
//	    if (user2.getFollowers().contains(reqUser)) {
//	        // Unfollow logic (toggle)
//	        user2.getFollowers().remove(reqUser);
//	        reqUser.getFollowing().remove(user2);
//	    } else {
//	        // Follow logic
//	        user2.getFollowers().add(reqUser);
//	        reqUser.getFollowing().add(user2);
//	    }
//
//	    userRepository.save(user2);
//	    userRepository.save(reqUser);
//
//    return reqUser;
//	}


	@Override
	public User updateUser(User user, Integer userId) throws Exception {

		Optional<User> user1 = userRepository.findById(userId);
		if (user1.isEmpty()) {
			throw new Exception("User Does not Exist");
		}
		User oldUser = user1.get();
		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}
		
		User updateUser = userRepository.save(oldUser);
		return updateUser;
	}

	@Override
	public List<User> searchUser(String query) {

		return userRepository.searchUser(query);
	}

	public User findUserByJwt(String jwt) {
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		
		User user=userRepository.findByEmail(email);
		return user;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
