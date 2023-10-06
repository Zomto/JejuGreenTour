package com.jejugreentour.jgt.accom.controller;

import com.jejugreentour.jgt.accom.service.AccomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accom")
@RequiredArgsConstructor
public class AccomController {
    private final AccomService accomService;

    // 숙박 등록 페이지로 이동
    @GetMapping("/addAccomForm")
    public String addAccomForm(){
        return "content/accom/accom_add";
    }
}
