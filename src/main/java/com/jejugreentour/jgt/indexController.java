package com.jejugreentour.jgt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("/")
    public  String main(){
        return "index";
    }
}
