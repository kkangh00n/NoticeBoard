package com.SenierProject.NoticeBoard.web.dto.postdto;

import com.SenierProject.NoticeBoard.web.dto.commentdto.CommentResponseDto;
import com.SenierProject.NoticeBoard.domain.posts.Posts;
import com.SenierProject.NoticeBoard.domain.user.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private String email;

    private String roomId;

    private User user;

    private List<CommentResponseDto> comments;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.email = entity.getEmail();
        this.roomId = entity.getRoomId();
        this.user = entity.getUser();
        this.comments = entity.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
