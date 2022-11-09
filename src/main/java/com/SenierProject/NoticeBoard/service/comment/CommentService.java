package com.SenierProject.NoticeBoard.service.comment;

import com.SenierProject.NoticeBoard.web.dto.commentdto.CommentRequestDto;
import com.SenierProject.NoticeBoard.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.domain.comment.Comment;
import com.SenierProject.NoticeBoard.domain.comment.CommentRepository;
import com.SenierProject.NoticeBoard.domain.posts.Posts;
import com.SenierProject.NoticeBoard.domain.posts.PostsRepository;
import com.SenierProject.NoticeBoard.domain.user.User;
import com.SenierProject.NoticeBoard.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;     /* CREATE */

    private final UserRepository userRepository;

    @Transactional
    public Long commentSave(SessionUser sessionuser, Long id, CommentRequestDto dto) {

        Posts post = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        User user = userRepository.findById(sessionuser.getId()).orElseThrow(()
                -> new IllegalArgumentException("작성자 x" + sessionuser.getId()));
        //========= comment에 user와 post 저장 =============
        dto.setUser(user);
        dto.setPosts(post);

        //========= dto 정보를 Comment 객체에 저장 후 repository 저장
        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }
}

