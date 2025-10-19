package com.tka.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.Models.Reels;
import com.tka.Models.User;
import com.tka.Repository.ReelsRepository;

@Service
public class ReelsServiceImpl implements ReelsService {

	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Reels createReels(Reels reel, User user) {

	    Reels createReel = new Reels();
	    createReel.setTitle(reel.getTitle());
	    createReel.setVideo(reel.getVideo());
	    createReel.setUser(user);  // ✅ set the logged-in user, not reel.getUser()

	    return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception {
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
