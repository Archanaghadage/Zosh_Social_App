package com.tka.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;
    private String image;
    private String video;
    private LocalDate createdAt;

    // ---------------- CREATOR ----------------
    @ManyToOne
    @JoinColumn(name = "user_id") // explicit foreign key column
    private User user;

    // ---------------- COMMENTS ----------------
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id") // this creates post_id in comments table
    private List<Comment> comments = new ArrayList<>();

    // ---------------- LIKES ----------------
    @ManyToMany
    @JoinTable(
        name = "post_likes",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> liked = new ArrayList<>();

    // ---------------- SAVED BY USERS ----------------
    @ManyToMany(mappedBy = "savedPosts")
    private List<User> savedByUsers = new ArrayList<>();

    // ---------------- CONSTRUCTORS ----------------
    public Post() {}

    public Post(Integer id, String caption, String image, String video, User user,
                LocalDate createdAt, List<Comment> comments, List<User> liked) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.user = user;
        this.createdAt = createdAt;
        this.comments = comments;
        this.liked = liked;
    }

    // ---------------- GETTERS & SETTERS ----------------
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getVideo() { return video; }
    public void setVideo(String video) { this.video = video; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public List<User> getLiked() { return liked; }
    public void setLiked(List<User> liked) { this.liked = liked; }

    public List<User> getSavedByUsers() { return savedByUsers; }
    public void setSavedByUsers(List<User> savedByUsers) { this.savedByUsers = savedByUsers; }
}