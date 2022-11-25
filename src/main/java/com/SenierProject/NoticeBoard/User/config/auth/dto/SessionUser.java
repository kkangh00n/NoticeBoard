package com.SenierProject.NoticeBoard.User.config.auth.dto;

import com.SenierProject.NoticeBoard.PostandComment.comments.domain.Comment;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.User.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SessionUser implements Serializable {

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
