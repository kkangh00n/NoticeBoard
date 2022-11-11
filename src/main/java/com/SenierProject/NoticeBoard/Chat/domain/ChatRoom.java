package com.SenierProject.NoticeBoard.Chat.domain;

import com.SenierProject.NoticeBoard.User.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.User.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    private String roomId;
    private String roomName;
    private Map<User, String> sessions = new HashMap<>();          //User, User.name
    private Integer session_Count;



    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.session_Count=0;
        return room;
    }

    public int addSession(User sessionUser, String name){
        sessions.put(sessionUser, name);
        return ++session_Count;
    }
    public int minusSession(User sessionUser , String name){
        sessions.remove(sessionUser);
        return --session_Count;
    }
}
