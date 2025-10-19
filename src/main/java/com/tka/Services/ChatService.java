package com.tka.Services;

import java.util.List;

import com.tka.Models.Chat;
import com.tka.Models.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
	
	
}
