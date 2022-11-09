package com.SenierProject.NoticeBoard.web.dto.commentdto;

import com.SenierProject.NoticeBoard.domain.comment.Comment;
import com.SenierProject.NoticeBoard.domain.posts.Posts;
import com.SenierProject.NoticeBoard.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {
    private Long id;
    private Posts posts;
    private User users;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.posts=comment.getPosts();
        this.users=comment.getUser();
    }
}
