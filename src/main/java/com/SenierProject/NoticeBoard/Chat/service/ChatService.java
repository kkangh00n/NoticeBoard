package com.SenierProject.NoticeBoard.Chat.service;

import com.SenierProject.NoticeBoard.Chat.chatdto.ChatMessage;
import com.SenierProject.NoticeBoard.Chat.domain.ChatRoom;
import com.SenierProject.NoticeBoard.Chat.domain.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;


    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        return chatRoomRepository.findAllRoom();
    }

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

    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

//    public void sendChatMessage(ChatMessage chatMessage) {
//        chatMessage.setUserCount(chatRoomRepository.getUserCount(chatMessage.getRoomId()));
//        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
//            chatMessage.setMessage(chatMessage.getSender() + "님이 방에 입장했습니다.");
//            chatMessage.setSender("[알림]");
//        } else if (ChatMessage.MessageType.QUIT.equals(chatMessage.getType())) {
//            chatMessage.setMessage(chatMessage.getSender() + "님이 방에서 나갔습니다.");
//            chatMessage.setSender("[알림]");
//        }
//        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
//    }
}
