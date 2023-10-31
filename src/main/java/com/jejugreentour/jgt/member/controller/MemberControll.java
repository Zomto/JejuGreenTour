package com.jejugreentour.jgt.member.controller;

import com.jejugreentour.jgt.member.mail.SendMail;
import com.jejugreentour.jgt.member.service.MemberService;
import com.jejugreentour.jgt.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import java.util.List;


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
    @GetMapping("/find")
    public String findIdPw(){
        return "content/member/find_IdPw";
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
    public int verifyCode(String email){

        String MailSender= SendMail.generateRandomCode(6);

        SendMail.setAndsend("제주그린투어 인증 메일 입니다." ,"인증번호 : "+MailSender +"입니다." ,"jejugreentour" ,"jejugreen123!" ,email);

        return Integer.parseInt(MailSender);
    }

    @ResponseBody
    @PostMapping("/modifyPw")
    public int modifyPw(String email){
        String MailSender= SendMail.generateRandomCode(6);
        SendMail.setAndsend("제주그린투어 비밀번호 변경 메일 입니다", "변경된 비밀 번호:"+MailSender +"입니다. 로그인후 마이페이지에서 비밀번호를 변경해주세요","jejugreentour" ,"jejugreen123!" ,email);
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

    @PostMapping("/editMember1")
    public String updateMember1(MemberVO memberVO, HttpSession session) {
        System.out.println(memberVO);
        MemberVO vo =(MemberVO)session.getAttribute("loginInfo");
        memberVO.setMemberId(vo.getMemberId());
        // MemberService를 사용하여 비밀번호 업데이트를 처리
        int updatedRows = memberService.updateMember1(memberVO);

        if (updatedRows > 0) {
            // 업데이트 성공 시 어떤 처리를 하거나 리다이렉트할 수 있음
        }

        // 리다이렉트 또는 다른 처리
        return "content/member/member_info";
    }
    @PostMapping("/editMember2")
    public String updateMember2(MemberVO memberVO, HttpSession session) {
        System.out.println(memberVO);
        MemberVO vo =(MemberVO)session.getAttribute("loginInfo");
        memberVO.setMemberId(vo.getMemberId());
        // MemberService를 사용하여 비밀번호 업데이트를 처리
        int updatedRows = memberService.updateMember2(memberVO);

        if (updatedRows > 0) {
            // 업데이트 성공 시 어떤 처리를 하거나 리다이렉트할 수 있음
        }

        // 리다이렉트 또는 다른 처리
        return "redirect:/member/logout";
    }
    @GetMapping("/find_IdForm")
    public String findIdForm(){
        return "content/member/find_Id";
    }

    @ResponseBody
    @PostMapping("/findId")
    public List<MemberVO> findId(String member_mail1) {
        String input = member_mail1;
        String member_mail = input.replace("%40", "@");
        List<MemberVO> members = memberService.findId(member_mail);

        return members;
    }
    @GetMapping("/find_PwForm")
    public String findPwForm(){
        return "content/member/find_Pw";
    }


    @ResponseBody
    @PostMapping("/changePw")
    public String updatePw(MemberVO memberVO) {
        memberService.updatePw(memberVO);
        return "redirect:content/member/login";
    }


}
