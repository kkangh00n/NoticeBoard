package com.SenierProject.NoticeBoard.PostandComment.posts;

import com.SenierProject.NoticeBoard.Chat.service.ChatService;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.PostandComment.posts.service.PostsService;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsListResponseDto;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsResponseDto;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsSaveRequestDto;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    private final ChatService chatService;

    @PostMapping("/api/v1/posts/{id}")       //게시물 저장       id = user의 id
    public Long save (@PathVariable Long id, @RequestBody PostsSaveRequestDto requestDto){

        return postsService.save(id, requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")       //게시글 수정
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")    //게시글 삭제, 채팅방 삭제
    public Long delete(@PathVariable Long id) {
        Posts response = postsService.findById(id);      //채팅방 RoomId
        chatService.deleteRoom(response.getRoomId());
        postsService.delete(id);
        return id;
    }
}
