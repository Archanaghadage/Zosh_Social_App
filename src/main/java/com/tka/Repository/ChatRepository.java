package com.tka.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tka.Models.Chat;
import com.tka.Models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	public List<Chat> findByUserId(Integer userId);
	
	@Query("select c from Chat c where :user Memeber of c.user And :reqUser Memeber of c.usr ")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

}
