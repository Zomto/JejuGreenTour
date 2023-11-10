package com.jejugreentour.jgt;

import com.jejugreentour.jgt.accom.service.AccomService;
import com.jejugreentour.jgt.accom.vo.AccomCategoryVO;
import com.jejugreentour.jgt.accom.vo.SubAccomImgVO;
import com.jejugreentour.jgt.accom.vo.SubAccomVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@RequiredArgsConstructor
@Controller

public class indexController {
    private final AccomService accomService;

    @GetMapping("/")
    public  String main(Model model){
        List<SubAccomVO> vo = accomService.showMain();
        model.addAttribute("subAccom", vo);
        System.out.println(vo);


        List<String> accomList = accomService.selectDistinctAccomLoc();


        Map<String, List<SubAccomVO>> map = new HashMap<>();

        for(String accom : accomList){
            List<SubAccomVO> listPerAccomLoc = new ArrayList<>();
            int cnt = 0;

            for(SubAccomVO e : vo){

                if(accom.equals(e.getAccomLoc())){
                    listPerAccomLoc.add(e);
                    cnt++;

                    if(cnt >= 10){
                        break;
                    }
                }

            }
            map.put(accom, listPerAccomLoc);
        }


        System.out.println(map.toString());

        model.addAttribute("myData", map);

        ///////////////////////////////////////
        //테마별
        String[] themes = {"풀빌라", "오션_뷰","PC_룸","N플릭스","금연", "흡연_O", "반려동물_O", "반려동물_X", "노키즈_존"};
        List<String> themeList = Arrays.asList(themes);

        Map<String, List<SubAccomVO>> themeMap = new HashMap<>();

        for(String theme : themes){

            List<SubAccomVO> aList = new ArrayList<>();
            int cnt = 0;

            for(SubAccomVO e : vo){
                if(e.getSubAccomCate().contains(",")&&Arrays.asList(e.getSubAccomCate().split(",")).contains(theme)){
                    aList.add(e);
                    cnt++;

                    if(cnt >= 10){
                        break;
                    }
                }
            }
            themeMap.put(theme, aList);
        }

        model.addAttribute("themeMap", themeMap);




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
