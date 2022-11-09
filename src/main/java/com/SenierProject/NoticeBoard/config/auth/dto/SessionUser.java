package com.SenierProject.NoticeBoard.config.auth.dto;

import com.SenierProject.NoticeBoard.domain.comment.Comment;
import com.SenierProject.NoticeBoard.domain.posts.Posts;
import com.SenierProject.NoticeBoard.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionUser {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private List<Posts> posts;
    private List<Comment> comments;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.posts = user.getPosts();
        this.comments = user.getComments();
    }
}
