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

//        model.addAttribute("checklist",checkVOList);
        return "/buy/calendar";
    }
    @GetMapping("/adminCalendar")
    public String adminCalendar() {

        return"/buy/admin_calendar";
    }
}
