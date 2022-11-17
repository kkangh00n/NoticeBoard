package com.SenierProject.NoticeBoard.PostandComment.comments.domain;

import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT p FROM Comment p WHERE p.comment_posts=?1")
    List<Comment> findByComment_posts(Posts posts);
}
