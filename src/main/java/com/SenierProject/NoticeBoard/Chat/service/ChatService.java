package com.SenierProject.NoticeBoard.Chat.service;

import com.SenierProject.NoticeBoard.Chat.domain.ChatRoom;
import com.SenierProject.NoticeBoard.Chat.domain.ChatRoomRepository;
import com.SenierProject.NoticeBoard.User.config.auth.LoginUser;
import com.SenierProject.NoticeBoard.User.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRoomRepository.findById(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        return chatRoomRepository.createRoom(name);
    }

    //채팅방 삭제
    public void deleteRoom(String roomId){
        chatRoomRepository.deleteRoom(roomId);
    }

}
