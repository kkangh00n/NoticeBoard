package com.SenierProject.NoticeBoard.Chat.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    private String roomId;
    private String roomName;
    private Map<String, String> sessions;         //User.id, User.name




    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.sessions = new HashMap<>();
        return room;
    }

    public int addSession(String userId, String name){
        sessions.put(userId, name);
        return sessions.size();
    }
    public int minusSession(String userId){
        sessions.remove(userId);
        return sessions.size();
    }
}
