package com.SenierProject.NoticeBoard.Chat;

import com.SenierProject.NoticeBoard.Chat.chatdto.ChatMessage;
import com.SenierProject.NoticeBoard.Chat.domain.ChatRoom;
import com.SenierProject.NoticeBoard.Chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")                            //채팅방에서 보낸 메시지 catch
    public void enter(@Payload ChatMessage message) {           //방 id, 현재 유저 id & name를 담고있는 ChatMessage객체

        ChatRoom room = chatService.findById(message.getRoomId());      //현재 채팅방 객체

        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {      //현재 채팅방에서 보낸 메시지 타입이 입장용이라면
            String realSender = message.getSender();

            //그 방 세션리스트에 현재 세션 추가
            room.addSession(message.getSessionId(), message.getSender());

            message.setSender("[알림]");
            message.setMessage(realSender + "님이 입장하였습니다.");
        }
        else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {  //현재 채팅방에서 보낸 메시지 타입이 퇴장용이라면
            String realSender = message.getSender();

            //그 방 세션리스트에 현재 세션을 제외
            room.minusSession(message.getSessionId());

            message.setSender("[알림]");
            message.setMessage(realSender + "님이 퇴장하였습니다.");
        }
        message.setUserCount(room.getSessions().size());          //현재 채팅방에 세션유저 수를 ChatMessage객체에 전달

        //room sessions -> message sessions 넘김
        List<String> sessions = room.getSessions().entrySet().stream().map(a -> a.getValue()).collect(Collectors.toList());

        message.setSessions(sessions);
        log.info("{}",(room.getSessions().values()));                //현재 채팅방에 입장 유저 목록
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);          //메시지 타입이 대화용일 때
    }
}
