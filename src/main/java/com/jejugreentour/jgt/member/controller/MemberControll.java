package com.jejugreentour.jgt.member.controller;

import com.jejugreentour.jgt.buy.service.BuyService;
import com.jejugreentour.jgt.buy.vo.BasketAccomVO;
import com.jejugreentour.jgt.buy.vo.ReservationVO;
import com.jejugreentour.jgt.member.mail.SendMail;
import com.jejugreentour.jgt.member.service.MemberService;
import com.jejugreentour.jgt.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberControll {
    @Resource
    private final BuyService buyService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/joinMember")
    public String joinMember(MemberVO memberVO){
        String encodedPassword = passwordEncoder.encode(memberVO.getMemberPw());
        memberVO.setMemberPw(encodedPassword);
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

    @GetMapping("/myPageForm")
    public String myPageForm(Authentication authentication,Model model){
        List<ReservationVO> list=new ArrayList<>();
        List<BasketAccomVO> basketAccomVOListlist= new ArrayList<>();
        if(authentication !=null){
            User user = (User)authentication.getPrincipal();
            list= buyService.selectMemberReservationList(user.getUsername());
            basketAccomVOListlist=buyService.selectBasketAccomList(user.getUsername());
        }
        model.addAttribute("Reservationlist",list);
        model.addAttribute("basketList",basketAccomVOListlist);
        return "content/member/myPage_main";
    }
    @GetMapping("/infoForm")
    public String infoForm(){
        return "content/member/member_info";
    }

    @GetMapping("/loginResult")
    public String loginResult(){
        return "content/member/login_result";
    }

    @PostMapping("/editMember1")
    public String updateMember1(MemberVO memberVO, HttpSession session) {
        MemberVO vo =(MemberVO)session.getAttribute("loginInfo");
        memberVO.setMemberId(vo.getMemberId());
        System.out.println(memberVO);
        // MemberService를 사용하여 비밀번호 업데이트를 처리
        int updatedRows = memberService.updateMember1(memberVO);

        if (updatedRows > 0) {
            // 업데이트 성공 시 어떤 처리를 하거나 리다이렉트할 수 있음
        }

        // 리다이렉트 또는 다른 처리
        return "redirect:/";
    }

    @PostMapping("/editMember2")
    public String updateMember2(HttpSession session, MemberVO memberVO, Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        //String userPw = memberService.getUserPw(authentication.getName());
        //System.out.println("111111111111" + userPw);
        String encodedPw = passwordEncoder.encode(memberVO.getMemberPw());
        memberVO.setMemberPw(encodedPw);
        memberVO.setMemberId(user.getUsername());
        System.out.println(memberVO);
        // MemberService를 사용하여 비밀번호 업데이트를 처리
        memberService.updateMember2(memberVO);

        // 리다이렉트 또는 다른 처리
        return "redirect:/member/logout";

    }

    @ResponseBody
    @PostMapping("/changePw")
    public void changePw(String memberId, String memberPw) {
        String encodedPw = passwordEncoder.encode(memberPw);
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(memberId);
        memberVO.setMemberPw(encodedPw);
        System.out.println(encodedPw);
        memberService.changePw(memberVO);
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

    @ResponseBody
    @PostMapping("/checkInfo")
    public String checkInfo(String memberId,String member_mail, MemberVO memberVO){
        MemberVO memberVO1 = new MemberVO();
        memberVO1.setMemberId(memberId);
        memberVO1.setMember_mail(member_mail);

        System.out.println(memberService.checkInfo(memberVO1));

        return memberService.checkInfo(memberVO1) == null ? "false" :memberService.checkInfo(memberVO1);
    }


    @GetMapping("/find_PwForm")
    public String findPwForm(){
        return "content/member/find_Pw";
    }


    @ResponseBody

    @PostMapping("/findPw")
    public void findPw(@RequestBody MemberVO memberVO) {
        String input = memberVO.getMember_mail();
        String member_mail = input.replace("%40", "@");
        // 이제 memberVO 객체에는 새로운 비밀번호 정보가 포함됩니다.
        memberService.changePw(memberVO);
    }



    @GetMapping("/changePwForm")
    public String changePwForm(String memberId, Model model){
        model.addAttribute("memberId", memberId);
        System.out.println(memberId);
        return "content/member/changePw";
    }
    @GetMapping("/myPagePlan")
    public String myPagePlan(Authentication authentication,Model model){
        if(authentication !=null){
            User user = (User)authentication.getPrincipal();
            String memberId= user.getUsername();
            List<ReservationVO> list=buyService.selectPlanList(memberId);
            model.addAttribute("Reservationlist",list);
        }
        return "content/member/myPage_plan";
    }
    @GetMapping("/myPageReview")
    public String myPageReview(Authentication authentication,Model model){
        String Id="";
        if(authentication !=null){
            User user = (User)authentication.getPrincipal();
            model.addAttribute("userName" ,user.getUsername());
            Id= user.getUsername();
        }
        System.out.println(buyService.memberReviewList(Id));
        model.addAttribute("myReviewList",buyService.memberReviewList(Id));

        return "content/member/myPage_review";
    }

}