package com.jejugreentour.jgt.csCenter.controller;


import com.jejugreentour.jgt.csCenter.service.CsService;
import com.jejugreentour.jgt.csCenter.vo.*;
import com.jejugreentour.jgt.member.service.MemberService;
import com.jejugreentour.jgt.search.service.SearchService;
import com.jejugreentour.jgt.search.vo.SearchVO;
import com.jejugreentour.jgt.util.UploadUtillCs;
import com.jejugreentour.jgt.util.UploadUtillRes;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
public class CsController {

    private final CsService csService;
    private final SearchService searchService;
    private final MemberService memberService;




    // QNA 목록 페이지 이동
    @RequestMapping("/qnaListForm")
    public String qnaListForm(Model model, SearchVO searchVO){
        // 페이지 정보 세팅
        searchVO.setTotalDataCnt(searchService.searchQnaCnt(searchVO));
        searchVO.setPageInfo();
        //model.addAttribute("qnaList", csService.qnaList(qnaVO));
        model.addAttribute("qnaList", searchService.searchQnaPaging(searchVO));

        return "/content/csCenter/qnaList";
    }

    // QNA 작성 페이지 이동
    @GetMapping("/qnaForm")
    public String qnaForm(){
        return "/content/csCenter/qna";
    }
    
    // QNA 작성 후 목록 페이지 이동
    @PostMapping("/insertQna")
    public String insertQna(QnaVO qnaVO){
        csService.insertQna(qnaVO);
        return "redirect:/cs/qnaListForm";
    }

    // qna 수정 페이지 이동
    @GetMapping("/updateQnaForm")
    public String updateQnaForm(Model model, String qnaCode){
//        model.addAttribute("qnaList", csService.selectQnaCnt(qnaVO));
        System.out.println(csService.selectQnaOne(qnaCode));
       QnaVO vo = csService.selectQnaOne(qnaCode);
       model.addAttribute("qna", vo);

        return "content/csCenter/updateQna";
    }

    // QNA 수정후 목록 페이지 이동
    @PostMapping("/updateQna")
    public String updateQna(QnaVO qnaVO){
        csService.updateQna(qnaVO);
        return "redirect:/cs/qnaListForm?qnaCode=" + qnaVO.getQnaCode();
    }

    // QNA 삭제
    @GetMapping("/deleteQna")
    public String deleteQna(String qnaCode){
        csService.deleteQna(qnaCode);
        return "redirect:/cs/qnaListForm";
    }

    ////////////////////////////////////////////////////////////


    // 공지 사항 메인 페이지
    @GetMapping("/csForm")
    public String csForm(AnnVO annVO, HttpSession session, Model model){
        model.addAttribute("qnaList", csService.qnaListOffset());
        model.addAttribute("annList", csService.annListOffset());
        System.out.println(csService.qnaListOffset());
        return "content/csCenter/csCenter_main";
    }


    // 공지 사항 목록 페이지
    @RequestMapping("/annListForm")
    public String annForm(Model model, AnnCateVO annCateVO, String annCate, SearchVO searchVO){
        // 페이지 정보 세팅
        searchVO.setTotalDataCnt(searchService.searchAnnCnt(searchVO));
        searchVO.setPageInfo();
        model.addAttribute("annList", searchService.searchAnnPaging(searchVO));
        model.addAttribute("cateList", csService.annCateList(annCateVO));
        if (annCate != null){
            model.addAttribute("annCate", annCate);
        }
        return "content/csCenter/annList";
    }


    // 공지 사항 작성 페이지
    @GetMapping("/annForm")
    public String snForm(HttpSession session, Model model, AnnVO annVO){
        return "content/csCenter/ann";
    }

    // 공지 사항 작성 후 목록 페이지 이동
    @PostMapping("/inputAnn")
    public String inputAnn(AnnVO annVO, HttpSession httpSession){

        csService.insertAnn(annVO);
        return "redirect:/cs/annListForm";
    }

    // 공지 사항 세부 페이지 이동
    @GetMapping("/annDetailForm")
    public String annDetailForm(AnnVO annVO, Model model){
        model.addAttribute("annDetail", csService.selectAnnDetail(annVO));
        return "content/csCenter/annDetail";
    }
    
    // 공지 사항 수정 페이지 이동
    @GetMapping("/updateAnnForm")
    public String updateAnnForm(AnnVO annVO, Model model){
        model.addAttribute("annDetail", csService.selectAnnDetail(annVO));
        return "content/csCenter/updateAnn";
    }

    // 공지사항 수정하기
    @PostMapping("/updateAnn")
    public String updateAnn(AnnVO annVO){
        csService.updateAnn(annVO);
        return "redirect:/cs/annDetailForm?annNum=" + annVO.getAnnNum();
    }
    
    // 공지사항 삭제하기
    @GetMapping("/deleteAnn")
    public String deleteNum(AnnVO annVO){
        csService.deleteAnn(annVO);
        return "redirect:/cs/annListForm";
    }


    // //////////////////////////////////////////////////////////


    // 문의 내역 목록 조회 페이지 이동
    @GetMapping("/inquireListForm")
    public String inquireListForm(Model model, InquireVO inquireVO){
        // 페이지 정보 세팅
        inquireVO.setTotalDataCnt(csService.selectInqCnt());
        inquireVO.setPageInfo();
        model.addAttribute("inqList", csService.InqList(inquireVO));
        System.out.println(csService.InqList(inquireVO));
        return "/content/csCenter/inquireList";
    }

    
    // 문의 하기 작성 페이지 이동
    @GetMapping("/inquireForm")
    public String inquireForm(Model model, String memberId){
        return "/content/csCenter/inquire";
    }

    // 문의 하기 세부 페이지
    @GetMapping("/inqDetailForm")
    public String inqDetailForm(String inqCode, Model model, String resCode){
        InquireVO inqVO = csService.inqDetail(inqCode);
        model.addAttribute("inqDetail", inqVO);
        model.addAttribute("inqImgList", csService.inqImgList(inqCode));

        if (inqVO.getIsResponse().equals("Y")){
            ResponseVO resVO = csService.selectResponse(inqCode);
            model.addAttribute("response", resVO);
            model.addAttribute("resImgList", csService.resImgList(resVO.getResCode()));
        }


        return "/content/csCenter/inqDetail";
    }

    @PostMapping("/insertInq")
    public String insertInq(InquireVO inquireVO, MultipartFile[] inqImg){
        //--- 상품 이미지 등록 ---//
        //0. 다음에 들어가야 할 ITEM_CODE를 조회
        String inqCode = csService.nextInqCode();

        //2. 이미지 정보 하나가 들어갈 수 있는 통!

        //첨부파일 기능 다중
        List<InqImgVO> imgList = UploadUtillCs.multiFileUpload(inqImg);


        for(InqImgVO inqImgVO : imgList){
            inqImgVO.setInqCode(inqCode);
        }

        inquireVO.setInqImgList(imgList);

        //상품 등록  + 이미지 등록 쿼리
        inquireVO.setInqCode(inqCode);
        csService.insertInq(inquireVO);
        return "redirect:/cs/inquireListForm";
    }

    @GetMapping("/deleteInq")
    public String deleteInq(String inqCode){
        csService.deleteInqImg(inqCode);
        csService.deleteInq(inqCode);
        return "redirect:/cs/inquireListForm";
    }

    @GetMapping("/responseForm")
    public String responseForm(Model model, String inqCode, InquireVO inquireVO){
        // 문의 내용 출력용
        model.addAttribute("inqDetail", csService.inqDetail(inqCode));
        model.addAttribute("inqImgList", csService.inqImgList(inqCode));
        System.out.println();
        return "content/csCenter/response";
    }

    @PostMapping("/insertResponse")
    public String insertResponse(ResponseVO responseVO, MultipartFile[] resImg, String inqCode){
        System.out.println(responseVO);
        //--- 상품 이미지 등록 ---//
        //0. 다음에 들어가야 할 ITEM_CODE를 조회
        String resCode = csService.nextResCode();

        //2. 이미지 정보 하나가 들어갈 수 있는 통!

        //첨부파일 기능 다중
        List<ResImgVO> imgList = UploadUtillRes.multiFileUpload(resImg);


        for(ResImgVO resImgVO : imgList){
            resImgVO.setResCode(resCode);
        }

        responseVO.setResImgList(imgList);

        //상품 등록  + 이미지 등록 쿼리
        responseVO.setResCode(resCode);
        csService.insertResponse(responseVO);
        csService.updateIsResponse(inqCode);

        return "redirect:/cs/inqDetailForm?inqCode=" + responseVO.getInqCode();
    }


}
