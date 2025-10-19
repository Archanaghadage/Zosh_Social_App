package com.tka.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tka.Models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
