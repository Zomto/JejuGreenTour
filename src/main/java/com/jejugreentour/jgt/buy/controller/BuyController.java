package com.jejugreentour.jgt.buy.controller;

import com.jejugreentour.jgt.buy.service.BuyService;
import com.jejugreentour.jgt.buy.vo.ReservationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping({"/buy"})
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;

    @GetMapping("/calendar")
    public String calendar(Model model){

//        List<ReservationVO> reservationVOList =buyService.selectReservation("SU_001");
//        System.out.println(reservationVOList);
        //List<ReservationVO> reservationVOList= new ArrayList<>();

        //model.addAttribute("Reservationlist",reservationVOList);
        return "/buy/calendar";
    }
    @GetMapping("/adminCalendar")
    public String adminCalendar() {

        return"/buy/admin_calendar";
    }

    @GetMapping("review")
    public  String reviewWrite(ReservationVO reservationVO){

        return"/buy/review";
    }

    @GetMapping("reviewList")
    public  String reviewList(ReservationVO reservationVO){

        return"/buy/review_list";
    }

}
