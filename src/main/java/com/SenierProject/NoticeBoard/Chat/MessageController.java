package com.SenierProject.NoticeBoard.Chat;

import com.SenierProject.NoticeBoard.Chat.chatdto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {

        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {      //메시지 타입이 입장용이라면
            String realSender = message.getSender();
            message.setSender("[알림]");
            message.setMessage(realSender + "님이 입장하였습니다.");
        }
        else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {
            String realSender = message.getSender();
            message.setSender("[알림]");
            message.setMessage(realSender + "님이 퇴장하였습니다.");
        }
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);          //메시지 타입이 대화용일 때
    }
}
