package com.jejugreentour.jgt.buy.controller;

import com.jejugreentour.jgt.buy.vo.CheckVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping({"/buy"})
public class BuyController {

    @GetMapping("/calendar")
    public String calendar(Model model){
        List<CheckVO> checkVOList=new ArrayList<>();
        CheckVO checkVO=new CheckVO();
        checkVO.setStart("2023-10-23");
        checkVO.setEnd("2023-10-25");
        checkVOList.add(checkVO);
        checkVO =new CheckVO();
        checkVO.setStart("2023-10-27");
        checkVO.setEnd("2023-10-30");
        checkVOList.add(checkVO);
        model.addAttribute("checklist",checkVOList);
        return "/buy/calendar";
    }
}
