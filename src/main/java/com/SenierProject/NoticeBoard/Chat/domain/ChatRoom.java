package com.SenierProject.NoticeBoard.Chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    private String roomId;
    private String roomName;

    private Long userCount;


    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.userCount = 0L;
        return room;
    }

    public Long plusUserCount(){
        return ++this.userCount;
    }

    public long minusUserCount(){
        return --this.userCount;
    }
}
