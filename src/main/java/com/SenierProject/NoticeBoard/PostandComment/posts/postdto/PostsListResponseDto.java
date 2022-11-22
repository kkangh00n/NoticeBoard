package com.SenierProject.NoticeBoard.PostandComment.posts.postdto;

import com.SenierProject.NoticeBoard.PostandComment.comments.commentdto.CommentResponseDto;
import com.SenierProject.NoticeBoard.PostandComment.comments.domain.Comment;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.User.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;
    private User user;
    private List<CommentResponseDto> comments;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.user = entity.getPost_user();
        this.comments = entity.getPost_comments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
