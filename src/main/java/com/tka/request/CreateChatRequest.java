package com.tka.request;

import org.springframework.web.bind.annotation.RequestBody;

import com.tka.Models.Chat;
import com.tka.Models.User;
import com.tka.Services.ChatService;

import lombok.Data;

@Data
public class CreateChatRequest {

	
	private Integer userId;
	
	
}
