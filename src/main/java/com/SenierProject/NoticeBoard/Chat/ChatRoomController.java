package com.SenierProject.NoticeBoard.Chat;

import com.SenierProject.NoticeBoard.Chat.domain.ChatRoom;
import com.SenierProject.NoticeBoard.Chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail() {
        return "/chat/roomdetail";
    }

}