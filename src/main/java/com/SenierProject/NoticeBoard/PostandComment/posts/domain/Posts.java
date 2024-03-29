package com.SenierProject.NoticeBoard.PostandComment.posts.domain;

import com.SenierProject.NoticeBoard.PostandComment.comments.domain.Comment;
import com.SenierProject.NoticeBoard.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity     //JPA에서 제공, 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String email;

    private String author;

    private String roomId;

    //====================== JPA 연결 ===============================
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User post_user;

    @OneToMany(mappedBy = "comment_posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> post_comments;
    //==========================================================

    @Builder
    public Posts(String title, String content, String author, String email, String roomId, User user, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.email = email;
        this.roomId = roomId;
        this.post_user = user;
        this.post_comments = comments;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
