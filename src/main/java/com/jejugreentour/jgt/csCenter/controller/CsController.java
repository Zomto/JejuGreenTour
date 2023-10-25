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
    public String csForm(AnnVO annVO, HttpSession session){
        return "content/csCenter/csCenter_main";
    }


    // 공지 사항 목록 페이지
    @GetMapping("/annForm")
    public String annForm(Model model, HttpSession session, AnnVO annVO){
        model.addAttribute("annList", csService.annList());
        System.out.println(csService.annList());
        return "content/csCenter/announcement";
    }

    // 공지 사항 작성 페이지
    @GetMapping("/snForm")
    public String snForm(HttpSession session, Model model, AnnVO annVO){
        return "content/csCenter/snTest";
    }

    // 공지 사항 작성 후 목록페이지 이동
    @PostMapping("/inputAnn")
    public String inputAnn(AnnVO annVO, HttpSession httpSession){

        csService.insertAnn(annVO);
        return "redirect:/cs/annForm";
    }

    // QNA 목록페이지 이동
    @GetMapping("/qnaForm")
    public String qnaForm(Model model){
        model.addAttribute("qnaList", csService.qnaList());
        return "/content/csCenter/qna";
    }


    // 문의하기 페이지 이동
    @GetMapping("/inquireForm")
    public String inquireForm(){

        return "/content/csCenter/inquire";
    }
    





}
