package com.jejugreentour.jgt.buy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({"/buy"})
public class BuyController {

    @GetMapping("/calendar")
    public String calendar(){
        return "/buy/calendar";
    }
}
