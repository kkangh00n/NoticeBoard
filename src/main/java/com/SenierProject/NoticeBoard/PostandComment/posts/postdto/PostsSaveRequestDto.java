package com.SenierProject.NoticeBoard.PostandComment.posts.postdto;

import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;       //o
    private String content;     //o
    private String author;      //o
    private String email;       //o
    private String roomId;
    private User user;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String email, String roomId, User user){
        this.title = title;
        this.content = content;
        this.author = author;
        this.email = email;
        this.roomId = roomId;
        this.user = user;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .email(email)
                .roomId(roomId)
                .user(user)
                .build();
    }
}
