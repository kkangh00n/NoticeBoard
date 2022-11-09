package com.SenierProject.NoticeBoard.web.controller;

import com.SenierProject.NoticeBoard.service.chat.ChatService;
import com.SenierProject.NoticeBoard.service.posts.PostsService;
import com.SenierProject.NoticeBoard.web.dto.postdto.PostsListResponseDto;
import com.SenierProject.NoticeBoard.web.dto.postdto.PostsResponseDto;
import com.SenierProject.NoticeBoard.web.dto.postdto.PostsSaveRequestDto;
import com.SenierProject.NoticeBoard.web.dto.postdto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    private final ChatService chatService;

    @PostMapping("/api/v1/posts/{id}")       //게시물 저장
    public Long save (@PathVariable Long id, @RequestBody PostsSaveRequestDto requestDto){

        return postsService.save(id, requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")       //게시글 수정
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("api/v1/posts/{id}")       //게시글 조회
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")    //게시글 삭제, 채팅방 삭제
    public Long delete(@PathVariable Long id) {
        PostsResponseDto response = postsService.findById(id);      //채팅방 RoomId
        chatService.deleteRoom(response.getRoomId());
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
