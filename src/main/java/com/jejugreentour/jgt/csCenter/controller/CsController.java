package com.jejugreentour.jgt.csCenter.controller;

import com.jejugreentour.jgt.csCenter.service.CsService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
public class CsController {
    @Resource
    private CsService csService;


    @GetMapping("/csForm")
    public String csForm(){


        return "content/csCenter/csCenter_main";
    }




}
