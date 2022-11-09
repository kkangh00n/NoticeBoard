package com.SenierProject.NoticeBoard.web.controller;

import com.SenierProject.NoticeBoard.web.dto.commentdto.CommentRequestDto;
import com.SenierProject.NoticeBoard.config.auth.LoginUser;
import com.SenierProject.NoticeBoard.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/posts/comments/{id}")
    public Long commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto, @LoginUser SessionUser sessionuser) {
        return commentService.commentSave(sessionuser, id, dto);
    }
}

