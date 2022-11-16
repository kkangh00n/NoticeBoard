package com.SenierProject.NoticeBoard.User.domain;

import com.SenierProject.NoticeBoard.PostandComment.comments.domain.Comment;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.BaseTimeEntity;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //====================== JPA 연결 ===============================
    @OneToMany(mappedBy = "post_user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 게시물 정렬
    private List<Posts> posts;

    @OneToMany(mappedBy = "comment_user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;
    //==========================================================

    @Builder
    public User(String name, String email, String picture, Role role, List<Posts> posts, List<Comment> comments) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.posts = posts;
        this.comments = comments;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
