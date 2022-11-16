package com.SenierProject.NoticeBoard.PostandComment.comments;

import com.SenierProject.NoticeBoard.PostandComment.comments.commentdto.CommentDto;
import com.SenierProject.NoticeBoard.PostandComment.comments.commentdto.CommentRequestDto;
import com.SenierProject.NoticeBoard.User.config.auth.LoginUser;
import com.SenierProject.NoticeBoard.User.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.PostandComment.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/posts/comments/{id}")            //id = 게시물 id
    public Long commentSave(@PathVariable Long id, @RequestBody CommentDto dto, @LoginUser SessionUser sessionuser) {
        return commentService.commentSave(sessionuser, id, dto);        //id = 게시물 id, dto = 저장할 댓글 정보
    }
}

