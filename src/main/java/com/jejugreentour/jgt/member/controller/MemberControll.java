package com.jejugreentour.jgt.member.controller;

import com.jejugreentour.jgt.member.mail.SendMail;
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
        System.out.println(memberVO);
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
        return "redirect:/";
    }
    @ResponseBody
    @PostMapping("/checkId")
    public boolean checkId(String memberId){
        return memberService.checkId(memberId);
    }


    @ResponseBody
    @PostMapping("/verifyCode")
    public int verifyCode(
            String email){

        String MailSender= SendMail.generateRandomCode(6);

        SendMail.setAndsend("제주그린투어 가입인증 메일 입니다." ,"인증번호 : "+MailSender +"입니다." ,"jejugreentour" ,"jejugreen123!" ,email);

        return Integer.parseInt(MailSender);
    }

    @GetMapping("/myPageForm")
    public String myPageForm(){
        return "content/member/myPage_main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    @GetMapping("/info")
    public String memberInfo(){
        return "content/member/member_info";
    }
}
