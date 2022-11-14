package com.SenierProject.NoticeBoard.PostandComment.comments.domain;

import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
