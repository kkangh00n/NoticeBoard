package com.SenierProject.NoticeBoard.Chat.chatdto;

import com.SenierProject.NoticeBoard.User.domain.User;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, QUIT
    }

    private MessageType type;
    //채팅방 ID
    private String roomId;
    //보내는 사람
    private String sender;
    //현재 접속 유저 id
    private String sessionId;
    //내용
    private String message;
    private List<String> sessions;
    //메시지를 보내는 채팅방에 세션 수
    private Integer userCount;


    @Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message, String sessionId, Integer userCount, List<String> sessions) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.sessionId = sessionId;
        this.userCount = userCount;
        this.sessions = sessions;
    }
}
