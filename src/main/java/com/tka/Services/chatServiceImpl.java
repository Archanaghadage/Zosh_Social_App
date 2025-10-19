package com.tka.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.Models.Chat;
import com.tka.Models.User;
import com.tka.Repository.ChatRepository;

@Service
public class chatServiceImpl implements ChatService {


	@Autowired
	private ChatRepository chatRepository;

    
	@Override
	public Chat createChat(User reqUser, User user2) {

		Chat isExits = chatRepository.findChatByUsersId(user2, reqUser);
		if (isExits != null) {
			return isExits;

		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		return chatRepository.save(chat);
	}

	@Override 
	public Chat findChatById(Integer chatId)  throws Exception{
		Optional<Chat> opt =chatRepository.findById(chatId);
		
		if(opt.isEmpty()) {
			throw new Exception("Chat Not Found"+chatId);
		}
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {

		return chatRepository.findByUserId(userId);
	}

}
