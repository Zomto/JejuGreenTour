package com.jejugreentour.jgt.csCenter.controller;

import com.jejugreentour.jgt.csCenter.service.CsService;
import com.jejugreentour.jgt.csCenter.vo.AnnVO;
import com.jejugreentour.jgt.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
public class CsController {
    @Resource
    private CsService csService;


    // 공지 사항 메인 페이지
    @GetMapping("/csForm")
    public String csForm(){
        return "content/csCenter/csCenter_main";
    }


    // 공지 사항 목록 페이지
    @GetMapping("/annForm")
    public String annForm(String annWriter, Model model){
        model.addAttribute("annList", csService.annList());
        return "content/csCenter/announcement";
    }

    // 공지 사항 작성 페이지
    @GetMapping("/snForm")
    public String snForm(HttpSession httpSession, Model model){
        return "content/csCenter/snTest";
    }

    // 공지 사항 작성 후 목록페이지 이동
    @PostMapping("/inputAnn")
    public String inputAnn(AnnVO annVO, HttpSession httpSession){
        annVO.setMemberId( ((MemberVO)httpSession.getAttribute("loginInfo")).getMemberId());

        csService.insertAnn(annVO);
        return "redirect:/cs/annForm";
    }





}
