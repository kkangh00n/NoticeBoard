package com.SenierProject.NoticeBoard.Chat.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    //채팅방 삭제
    public void deleteRoom(String name){
        chatRooms.remove(name);
    }

//    // 유저가 입장한 채팅방ID와 유저 세션ID 맵핑 정보 저장
//    public void setUserEnterInfo(String sessionId, String roomId) {
//        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
//    }
//
//    // 유저 세션으로 입장해 있는 채팅방 ID 조회
//    public String getUserEnterRoomId(String sessionId) {
//        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
//    }
//
//    // 유저 세션정보와 맵핑된 채팅방ID 삭제
//    public void removeUserEnterInfo(String sessionId) {
//        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
//    }

    // 채팅방 유저수 조회
    public long getUserCount(String roomId) {
        return findById(roomId).getUserCount();
    }

    // 채팅방에 입장한 유저수 +1
    public long plusUserCount(String roomId) {
        return findById(roomId).plusUserCount();
    }

    // 채팅방에 입장한 유저수 -1
    public long minusUserCount(String roomId) {
        return findById(roomId).minusUserCount();
    }
}
