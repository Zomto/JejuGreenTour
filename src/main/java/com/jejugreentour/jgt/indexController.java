package com.jejugreentour.jgt;

import com.jejugreentour.jgt.accom.service.AccomService;
import com.jejugreentour.jgt.accom.vo.AccomCategoryVO;
import com.jejugreentour.jgt.accom.vo.SubAccomImgVO;
import com.jejugreentour.jgt.accom.vo.SubAccomVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller

public class indexController {
    private final AccomService accomService;

    @GetMapping("/")
    public  String main(Model model){
        List<SubAccomVO> vo = accomService.showMain();
        model.addAttribute("subAccom", vo);
        System.out.println(vo);
        return "index";
    }


//    (Model model, String subAccomCode) {
//        SubAccomVO vo = accomService.selectSubAccomDetail(subAccomCode);
//        List<SubAccomImgVO> img = accomService.selectSubAccomImg(subAccomCode);
//        model.addAttribute("subAccom", vo);
//        System.out.println(vo);
//        System.out.println(img);
//        model.addAttribute("imgList", img);
//
//    }


}
