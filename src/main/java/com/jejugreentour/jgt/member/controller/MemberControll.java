package com.jejugreentour.jgt.member.controller;

import com.jejugreentour.jgt.member.service.MemberService;
import com.jejugreentour.jgt.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberControll {
    @Resource
    private MemberService memberService;

    @PostMapping("/joinMember")
    public String joinMember(MemberVO memberVO){
        memberService.join(memberVO);
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "content/member/join";
    }


    @GetMapping("/loginForm")
    public String loginForm(MemberVO memberVO){
        return "content/member/login";
    }

    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);

        if(loginInfo !=null){
            session.setAttribute("loginInfo",loginInfo);
        }
        return "content/member/login_result";
    }

//    @ResponseBody
//    @PostMapping("/checkId")
//    public boolean checkId(String memberId){
//        return memberService.checkId(memberId);
//    }



}
