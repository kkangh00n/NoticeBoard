package com.SenierProject.NoticeBoard.PostandComment.comments.domain;

import com.SenierProject.NoticeBoard.PostandComment.posts.domain.BaseTimeEntity;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.User.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용



    //====================== JPA ===============================
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User comment_user;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts comment_posts;
    //==========================================================

}

