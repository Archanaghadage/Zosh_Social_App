package com.tka.Models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    // ---------------- FOLLOWERS / FOLLOWING ----------------
    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    private List<User> following = new ArrayList<>();

    // ---------------- SAVED POSTS ----------------
    @ManyToMany
    @JoinTable(
        name = "user_saved_posts",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> savedPosts = new ArrayList<>();

    // ---------------- POSTS CREATED BY USER ----------------
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
    
    @ManyToMany(mappedBy = "users") // maps to the 'users' field in the Chat entity
    private List<Chat> chats = new ArrayList<>();

    // ---------------- CONSTRUCTORS ----------------
    public User() {}

   
    public User(Integer id, String firstName, String lastName, String email, String password, String gender,
			List<User> followers, List<User> following, List<Post> savedPosts, List<Post> posts, List<Chat> chats) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.followers = followers;
		this.following = following;
		this.savedPosts = savedPosts;
		this.posts = posts;
		this.chats = chats;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public List<User> getFollowers() {
		return followers;
	}


	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}


	public List<User> getFollowing() {
		return following;
	}


	public void setFollowing(List<User> following) {
		this.following = following;
	}


	public List<Post> getSavedPost() {
		return savedPosts;
	}


	public void setSavedPost(List<Post> savedPosts) {
		this.savedPosts = savedPosts;
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public List<Chat> getChats() {
		return chats;
	}


	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}


	
}