package com.tka.Services;

import java.util.List;

import com.tka.Models.Message;
import com.tka.Models.User;

public interface MessageService {

    Message createMessage(User user, Integer chatId, Message message) throws Exception;


	List<Message> findChatMessages(Integer chatId) throws Exception;
}
