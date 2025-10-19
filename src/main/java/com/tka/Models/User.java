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

    // Followers: users who follow THIS user
//    @ManyToMany
//    @JoinTable(
//        name = "user_followers",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "follower_id")
//    )
//    private List<User> followers = new ArrayList<>();
//
//    // Following: users that THIS user follows (mapped to followers)
//    @ManyToMany(mappedBy = "followers")
//    private List<User> following = new ArrayList<>();

    // Saved posts
    @ManyToMany
    @JoinTable(
        name = "user_saved_posts",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> savedPost = new ArrayList<>();

    // Constructors
    public User() {}

    public User(Integer id, String firstName, String lastName, String email, String password, String gender,
                 List<Post> savedPost) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
//        this.followers = followers;
//        this.following = following;
        this.savedPost = savedPost;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

//    public List<User> getFollowers() { return followers; }
//    public void setFollowers(List<User> followers) { this.followers = followers; }
//
//    public List<User> getFollowing() { return following; }
//    public void setFollowing(List<User> following) { this.following = following; }

    public List<Post> getSavedPost() { return savedPost; }
    public void setSavedPost(List<Post> savedPost) { this.savedPost = savedPost; }
    
    //List<User> followers, List<User> following,
}
