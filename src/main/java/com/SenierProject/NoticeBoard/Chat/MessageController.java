package com.SenierProject.NoticeBoard.Chat;

import com.SenierProject.NoticeBoard.Chat.chatdto.ChatMessage;
import com.SenierProject.NoticeBoard.Chat.domain.ChatRoom;
import com.SenierProject.NoticeBoard.Chat.domain.ChatRoomRepository;
import com.SenierProject.NoticeBoard.Chat.service.ChatService;
import com.SenierProject.NoticeBoard.User.config.auth.LoginUser;
import com.SenierProject.NoticeBoard.User.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.User.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    private final ChatService chatService;

    private final UserRepository userRepository;

    @MessageMapping("/chat/message")
    public void enter(@Payload ChatMessage message) {           //방 id, 현재 유저 id & name

        ChatRoom room = chatService.findById(message.getRoomId());      //현재 방 정보

        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {      //메시지 타입이 입장용이라면
            String realSender = message.getSender();

            //그 방에 세션 더해줌
            room.addSession(userRepository.findById(Long.parseLong(message.getSessionId())).get(), message.getSender());

            message.setSender("[알림]");
            message.setMessage(realSender + "님이 입장하였습니다.");
        }
        else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {
            String realSender = message.getSender();

            room.minusSession(userRepository.findById(Long.parseLong(message.getSessionId())).get(), message.getSender());

            message.setSender("[알림]");
            message.setMessage(realSender + "님이 퇴장하였습니다.");
        }
        message.setUserCount(room.getSession_Count());
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);          //메시지 타입이 대화용일 때
    }
}
