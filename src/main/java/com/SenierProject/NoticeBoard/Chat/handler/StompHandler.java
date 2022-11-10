package com.SenierProject.NoticeBoard.Chat.handler;

import com.SenierProject.NoticeBoard.Chat.chatdto.ChatMessage;
import com.SenierProject.NoticeBoard.Chat.domain.ChatRoomRepository;
import com.SenierProject.NoticeBoard.Chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("하이하이 나는강훈");
        return message;
    }
}
