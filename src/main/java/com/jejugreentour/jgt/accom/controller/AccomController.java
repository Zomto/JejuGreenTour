package com.jejugreentour.jgt.accom.controller;


import com.jejugreentour.jgt.accom.service.AccomService;
import com.jejugreentour.jgt.accom.vo.MainAccomImgVO;
import com.jejugreentour.jgt.accom.vo.MainAccomVO;
import com.jejugreentour.jgt.accom.vo.SubAccomImgVO;
import com.jejugreentour.jgt.accom.vo.SubAccomVO;
import com.jejugreentour.jgt.buy.service.BuyService;
import com.jejugreentour.jgt.member.vo.MemberVO;
import com.jejugreentour.jgt.util.UploadUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/accom")
@RequiredArgsConstructor
public class AccomController {
    private final AccomService accomService;
    private final BuyService buyService;

    // 숙박 등록 페이지로 이동
    @GetMapping("/addAccomForm")
    public String addAccomForm(){
        return "content/accom/accom_add";
    }

    // 숙박업소 등록
    @PostMapping("/addAccom")
    public String addAccom(MainAccomVO mainAccomVO, MultipartFile mainImg, MultipartFile[] subImg, Authentication authentication){

        User user = (User)authentication.getPrincipal();
        mainAccomVO.setAccomCeo(user.getUsername());

        // 0. 다음에 들어 가야 할 ITEM_CODE 조회
        String accomCode = accomService.selectNextAccomCode();

        // 2. 이미지 정보 하나가 들어갈 수 있는 통!
        // 첨부파일 기능
        MainAccomImgVO vo = UploadUtil.uploadFile(mainImg);

        // 첨부파일 기능 다중업로드
        List<MainAccomImgVO> mainAccomImgList = UploadUtil.multiFileUpload(subImg);
        mainAccomImgList.add(vo);

        for(MainAccomImgVO mainAccomImgVO : mainAccomImgList) {
            mainAccomImgVO.setAccomCode(accomCode);
        }

        mainAccomVO.setMainAccomImgList(mainAccomImgList);

        // 상품 등록 + 이미지 등록 쿼리
        mainAccomVO.setAccomCode(accomCode);
        accomService.addAccom(mainAccomVO);

        // 상품 이미지 정보 등록 쿼리

        return "redirect:/";
    }

    // 숙박 정보 수정페이지로 이동
    @GetMapping("/mainAccomDetail")
    public String mainAccomDetail(String accomCode, Model model, Authentication authentication){
        model.addAttribute("score",buyService.selectscore(accomCode));
        MainAccomVO vo = accomService.selectMainAccomDetail(accomCode);
        List<MainAccomImgVO> img = accomService.selectSubImg(accomCode);
        model.addAttribute("mAccom", vo);
        System.out.println(vo);
        System.out.println(img);
        if(authentication !=null){
            User user = (User)authentication.getPrincipal();
            model.addAttribute("userName" ,user.getUsername());
        }
        model.addAttribute("imgList", img);
        List<SubAccomVO> subList=accomService.selectSubAccomlist(accomCode);
        model.addAttribute("subList", subList);
        return "content/accom/accom_detail";
    }
    // 업소 이름 변경
    @ResponseBody
    @PostMapping("/updateMainAccomName")
    public void updateMainAccomName(MainAccomVO mainAccomVO){
        accomService.updateMainAccomName(mainAccomVO);
    }

    // 업소 주소 변경
    @ResponseBody
    @PostMapping("/updateMainAccomAddr")
    public void updateMainAccomAddr(MainAccomVO mainAccomVO){
        accomService.updateMainAccomAddr(mainAccomVO);
    }

    @GetMapping("/subAccomAddForm")
    public String subAccomAddForm(String accomCode, Model model){

        model.addAttribute("accomCode",accomCode);
        return "content/accom/subAccom_add";
    }

    @ResponseBody
    @PostMapping("/updateMainAccomSubImg")
    public List<MainAccomImgVO> updateMainAccomSubImg(String accomCode, MainAccomVO mainAccomVO, MultipartFile[] files, Model model){


        // 첨부파일 기능 다중업로드
        List<MainAccomImgVO> mainAccomImgList = UploadUtil.multiFileUpload(files);

        for(MainAccomImgVO mainAccomImgVO : mainAccomImgList) {
            mainAccomImgVO.setAccomCode(accomCode);
        }

        mainAccomVO.setMainAccomImgList(mainAccomImgList);

        // 상품 등록 + 이미지 등록 쿼리
        mainAccomVO.setAccomCode(accomCode);
        accomService.updateMainAccomSubImg(mainAccomVO);

        //MainAccomVO vo = accomService.selectMainAccomDetail(accomCode);
        return mainAccomImgList;

        // 상품 이미지 정보 등록 쿼리
    }

    @ResponseBody
    @PostMapping("/deleteSubImg")
    public List<MainAccomImgVO> deleteSubImg(String mainImgCode, String accomCode){
        accomService.deleteSubImg(mainImgCode);
        List<MainAccomImgVO> mainAccomImgList = accomService.selectSubImg(accomCode);
        return mainAccomImgList;
    }
    @PostMapping("/addSubAccom")
    public  String addSubAccom(SubAccomVO subAccomVO, MultipartFile mainImg){


        // 0. 다음에 들어 가야 할 ITEM_CODE 조회
        String subAccomCode = accomService.selectNextsubAccomCode();

        // 2. 이미지 정보 하나가 들어갈 수 있는 통!
        // 첨부파일 기능
        SubAccomImgVO vo = UploadUtil.uploadSubFile(mainImg);

        // 첨부파일 기능 다중업로드
        List<SubAccomImgVO> mainAccomImgList = new ArrayList<>();
        mainAccomImgList.add(vo);

        for(SubAccomImgVO subAccomImgVO : mainAccomImgList) {
            subAccomImgVO.setSubAccomCode(subAccomCode);
        }

        subAccomVO.setSubAccomImgList(mainAccomImgList);

        // 상품 등록 + 이미지 등록 쿼리
        subAccomVO.setSubAccomCode(subAccomCode);
        System.out.println(subAccomVO);
        accomService.addSubAccom(subAccomVO);
        // 상품 이미지 정보 등록 쿼리

        return "redirect:/";
    }


    @GetMapping("/subAccomDetail")
    public String subAccomDetail(Model model, String subAccomCode) {
        SubAccomVO vo = accomService.selectSubAccomDetail(subAccomCode);
        List<SubAccomImgVO> img = accomService.selectSubAccomImg(subAccomCode);
        model.addAttribute("subAccom", vo);
        System.out.println(vo);
        System.out.println(img);
        model.addAttribute("imgList", img);
        return "content/accom/subAccom_detail";
    }



    @ResponseBody
    @PostMapping("/subAccomUpdate")
    public void subAccomDelete(SubAccomVO subAccomVO){
        System.out.println(subAccomVO);
        accomService.updateSubAccomState(subAccomVO);
    }

    @GetMapping("/mainAccomForm")
    public String mainAccomForm(MainAccomVO mainAccomVO, String accomLoc, Model model){
        // 페이지 정보 세팅
        mainAccomVO.setTotalDataCnt(accomService.selectAccomCnt(accomLoc));
        mainAccomVO.setPageInfo();
        model.addAttribute("accomList", accomService.mainAccomTab(mainAccomVO));
        if (accomLoc != null){
            model.addAttribute("accomLoc", accomLoc);
        }
        System.out.println(mainAccomVO);
        return "/content/accom/accom_list";
    }






}
