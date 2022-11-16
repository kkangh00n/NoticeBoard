package com.SenierProject.NoticeBoard.PostandComment.comments.commentdto;

import com.SenierProject.NoticeBoard.PostandComment.comments.domain.Comment;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.User.domain.User;
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
        this.posts=comment.getComment_posts();
        this.users=comment.getComment_user();
    }
}
