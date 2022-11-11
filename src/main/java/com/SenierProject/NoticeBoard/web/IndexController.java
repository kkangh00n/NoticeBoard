package com.SenierProject.NoticeBoard.web;

import com.SenierProject.NoticeBoard.PostandComment.comments.commentdto.CommentResponseDto;
import com.SenierProject.NoticeBoard.User.config.auth.LoginUser;
import com.SenierProject.NoticeBoard.User.config.auth.dto.SessionUser;
import com.SenierProject.NoticeBoard.User.domain.User;
import com.SenierProject.NoticeBoard.User.domain.UserRepository;
import com.SenierProject.NoticeBoard.PostandComment.posts.service.PostsService;
import com.SenierProject.NoticeBoard.PostandComment.posts.postdto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserRepository userRepository;

    private final PostsService postsService;

    @GetMapping("/")            //기본 페이지
    public String index(Model model, @LoginUser SessionUser currentuser){

        model.addAttribute("posts", postsService.findAllDesc());
        if (currentuser != null){
            model.addAttribute("myName", currentuser.getName());
            model.addAttribute("myEmail", currentuser.getEmail());
        }
        return "index";
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

    @GetMapping("/posts/lookup/{id}")       //글 조회 페이지  (인가 필요)
    public String postsLookup(@PathVariable Long id, Model model, @LoginUser SessionUser currentuser){
        PostsResponseDto dto = postsService.findById(id);       //게시글 정보 찾음
        List<CommentResponseDto> comments = dto.getComments();  //게시글의 댓글 정보 찾음

        User user = userRepository.findById(currentuser.getId()).get();

        model.addAttribute("post", dto);
        model.addAttribute("user", currentuser);

        // 댓글 관련
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        //사용자 인가 관련
        if (dto.getUser().equals(user)){
            model.addAttribute("writer", true);
        }

        return "posts-my-lookup";
    }
}
