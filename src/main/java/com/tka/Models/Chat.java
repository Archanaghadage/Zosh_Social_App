package com.tka.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String chat_name;
	
	private String chat_image;
	
	@ManyToMany 
    @JoinTable(
        name = "chat_users", // The name of the intermediate join table
        joinColumns = @JoinColumn(name = "chat_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users=new ArrayList<>();

	@OneToMany(mappedBy="chat")
	private List<Message> messages=new ArrayList<>();
	
	private LocalDateTime timestamp;
	
	
}
