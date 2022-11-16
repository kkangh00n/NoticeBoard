package com.SenierProject.NoticeBoard.web;

import com.SenierProject.NoticeBoard.PostandComment.comments.commentdto.CommentResponseDto;
import com.SenierProject.NoticeBoard.PostandComment.comments.domain.Comment;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.Posts;
import com.SenierProject.NoticeBoard.PostandComment.posts.domain.PostsRepository;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsListResponseDto;
import com.SenierProject.NoticeBoard.User.config.auth.LoginUser;
import com.SenierProject.NoticeBoard.User.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.User.domain.User;
import com.SenierProject.NoticeBoard.User.domain.UserRepository;
import com.SenierProject.NoticeBoard.PostandComment.posts.service.PostsService;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserRepository userRepository;

    private final PostsRepository postsRepository;

    private final PostsService postsService;

    @GetMapping("/")            //기본 페이지
    public String index(Model model, @LoginUser SessionUser currentuser){

        List<PostsListResponseDto> posts = postsService.findAllDesc();
//        try{
//            log.info("{}", posts.get(0).getComments().size());
//        }catch(IndexOutOfBoundsException e){
//            log.info("{}", "잠시 건너뜀");
//        }
        model.addAttribute("posts", posts);  //List<PostsListResponseDto> -> PostsListResponseDto-> List<CommentResponseDto>
        if (currentuser != null){
            model.addAttribute("myName", currentuser.getName());
            model.addAttribute("myEmail", currentuser.getEmail());
            model.addAttribute("myId", currentuser.getId());
        }
        return "index";
    }
    @GetMapping("/posts/lookup/{id}")       //글 조회 페이지  (인가 필요)         id = 게시물 id
    public String postsLookup(@PathVariable Long id, Model model, @LoginUser SessionUser currentuser){

        model.addAttribute("user", currentuser);    //현재 사용자 정보

        Posts dto = postsRepository.findById(id).get();       //게시글 정보 찾음
        List<Comment> comments = dto.getPost_comments();  //게시글의 댓글 정보 찾음
        User user = userRepository.findById(currentuser.getId()).get();     //현재 접속 유저정보 get (인가 위해)
        model.addAttribute("post", dto);
        // 댓글 관련
        model.addAttribute("comments", comments);
        //사용자 인가 관련
        if (dto.getPost_user().equals(user)){
            model.addAttribute("writer", true);
        }
        return "posts-my-lookup";
    }
    @GetMapping("/mypage/{id}")         //유저 활동 내역          id = userId
    public String mypageLookup(@PathVariable Long id, Model model){
        //특정 유저
        model.addAttribute("user", userRepository.findById(id).get());
        //특정 유저의 게시물
        model.addAttribute("post", userRepository.findById(id).get().getPosts());
        //특정 유저의 댓글
        model.addAttribute("comments", userRepository.findById(id).get().getComments());

        return "mypage";
    }

    @GetMapping("/posts/save")      //글 등록 페이지
    public String postsSave(Model model, @LoginUser SessionUser currentuser){

        model.addAttribute("currentuser", currentuser);
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")       //글 수정 페이지 (인가 필요)
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser currentuser){
        PostsResponseDto dto = postsService.findById(id);       //게시글 정보 찾음

        User user = userRepository.findById(currentuser.getId()).get();     //현재 접속자

        if(dto.getUser().equals(user)){         //게시글 유저와 현재 접속자가 같다면
            model.addAttribute("post", dto);
            return "posts-update";
        }
        return "redirect:/";
    }
}
