package com.SenierProject.NoticeBoard.PostandComment.comments.service;

import com.SenierProject.NoticeBoard.PostandComment.comments.commentdto.CommentDto;
import com.SenierProject.NoticeBoard.PostandComment.comments.commentdto.CommentRequestDto;
import com.SenierProject.NoticeBoard.User.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.PostandComment.comments.domain.Comment;
import com.SenierProject.NoticeBoard.PostandComment.comments.domain.CommentRepository;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.PostsRepository;
import com.SenierProject.NoticeBoard.User.domain.User;
import com.SenierProject.NoticeBoard.User.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;     /* CREATE */

    private final UserRepository userRepository;

    @Transactional
    public Long commentSave(SessionUser sessionuser, Long id, CommentDto dto) {
        //(현재 입장유저, 게시물 id, 저장할 댓글 정보)
        CommentRequestDto dto2 = new CommentRequestDto();

        Posts post = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        User user = userRepository.findById(sessionuser.getId()).orElseThrow(()
                -> new IllegalArgumentException("작성자 x" + sessionuser.getId()));
        //========= comment에 user와 post, 메시지 저장 =============
        dto2.setComment(dto.getComment());
        dto2.setUser(user);
        dto2.setPosts(post);

        Comment result = commentRepository.save(dto2.toEntity());
//        log.info("{}", result.getComment_posts().getPost_comments());      //댓글의 게시물 확인

        //========= dto 정보를 Comment 객체에 저장 후 repository 저장
        return result.getId();
    }

    @Transactional
    public void commentDelete(Long id){

        Comment comment = commentRepository.findById(id).get();
        commentRepository.delete(comment);

    }
}

