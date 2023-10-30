package com.jejugreentour.jgt.csCenter.controller;

import com.jejugreentour.jgt.csCenter.service.CsService;
import com.jejugreentour.jgt.csCenter.vo.AnnVO;
import com.jejugreentour.jgt.csCenter.vo.QnaVO;
import com.jejugreentour.jgt.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String csForm(AnnVO annVO, HttpSession session, Model model){
        model.addAttribute("qnaList", csService.qnaListOffset());
        model.addAttribute("annList", csService.annListOffset());
        System.out.println(csService.qnaListOffset());
        return "content/csCenter/csCenter_main";
    }


    // 공지 사항 목록 페이지
    @GetMapping("/annForm")
    public String annForm(Model model, HttpSession session, AnnVO annVO){
        // 페이지 정보 세팅
        annVO.setTotalDataCnt(csService.selectAnnCnt());
        annVO.setPageInfo();
        model.addAttribute("annList", csService.annList(annVO));
        return "content/csCenter/annList";
    }

    // 공지 사항 작성 페이지
    @GetMapping("/snForm")
    public String snForm(HttpSession session, Model model, AnnVO annVO){
        return "content/csCenter/ann";
    }

    // 공지 사항 작성 후 목록 페이지 이동
    @PostMapping("/inputAnn")
    public String inputAnn(AnnVO annVO, HttpSession httpSession){

        csService.insertAnn(annVO);
        return "redirect:/cs/annForm";
    }

    // 공지 사항 세부 페이지 이동
    @GetMapping("/annDetailForm")
    public String annDetailForm(AnnVO annVO, Model model){
        model.addAttribute("annDetail", csService.selectAnnDetail(annVO));
        return "content/csCenter/annDetail";
    }

    // QNA 목록 페이지 이동
    @GetMapping("/qnaForm")
    public String qnaForm(Model model, QnaVO qnaVO){
        // 페이지 정보 세팅
        qnaVO.setTotalDataCnt(csService.selectQnaCnt());
        qnaVO.setPageInfo();
        model.addAttribute("qnaList", csService.qnaList(qnaVO));
        return "/content/csCenter/qna";
    }

    // 문의 내역 페이지 이동
    @GetMapping("/inquireListForm")
    public String inquireListForm(){

        return "/content/csCenter/inquireList";
    }


    // 문의 하기 페이지 이동
    @GetMapping("/inquireForm")
    public String inquireForm(Model model){

        return "/content/csCenter/inquire";
    }
    





}
