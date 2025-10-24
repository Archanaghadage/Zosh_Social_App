package com.tka.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> liked = new ArrayList<>();

    private LocalDateTime createdAt;

    // ✅ Link comment to post
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {}

    public Comment(Integer id, String content, User user, List<User> liked,
                   LocalDateTime createdAt, Post post) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
        this.post = post;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<User> getLiked() { return liked; }
    public void setLiked(List<User> liked) { this.liked = liked; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", content=" + content + ", user=" + user +
               ", liked=" + liked + ", createdAt=" + createdAt + ", post=" + post + "]";
    }
}
