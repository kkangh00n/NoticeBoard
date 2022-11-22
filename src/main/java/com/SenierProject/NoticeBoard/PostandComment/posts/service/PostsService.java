package com.SenierProject.NoticeBoard.PostandComment.posts.service;

import com.SenierProject.NoticeBoard.Chat.domain.ChatRoom;
import com.SenierProject.NoticeBoard.Chat.service.ChatService;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.PostsRepository;
import com.SenierProject.NoticeBoard.User.domain.User;
import com.SenierProject.NoticeBoard.User.domain.UserRepository;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsListResponseDto;;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsSaveRequestDto;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {


    private final UserRepository usersRepository;
    private final PostsRepository postsRepository;

    private final ChatService chatService;

    @Transactional      //게시글 저장, 채팅방 생성
    public Long save(Long id, PostsSaveRequestDto requestDto){          //사용자 id,

        User user = usersRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        requestDto.setUser(user);                                   //게시글의 user 객체 저장

        ChatRoom chatRoom = chatService.createRoom(requestDto.getTitle());      //게시글에 제목으로 채팅방 생성
        requestDto.setRoomId(chatRoom.getRoomId());                             //채팅방 id를 dto에 넘김

        Posts result = postsRepository.save(requestDto.toEntity());

        return result.getId();         //게시물 저장
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public Posts findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//        log.info("댓글 갯수 {}", entity.getPost_comments().size());

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    public Posts findByRoomId (String roomId){
        return postsRepository.findByRoomId(roomId);
    }
}
