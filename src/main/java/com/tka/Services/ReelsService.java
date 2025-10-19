package com.tka.Services;

import java.util.List;

import com.tka.Models.Reels;
import com.tka.Models.User;

public interface ReelsService {

	public Reels createReels(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel(Integer userId) throws Exception;
}
