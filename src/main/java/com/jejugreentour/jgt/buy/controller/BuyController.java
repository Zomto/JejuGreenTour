package com.jejugreentour.jgt.buy.controller;

import com.jejugreentour.jgt.buy.service.BuyService;
import com.jejugreentour.jgt.buy.vo.BasketAccomVO;
import com.jejugreentour.jgt.buy.vo.ReservationVO;
import com.jejugreentour.jgt.buy.vo.SampleSubVO;
import com.jejugreentour.jgt.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping({"/buy"})
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;

    @GetMapping("/calendar")
    public String calendar(Model model ,String subAccomCode){

        List<ReservationVO> reservationVOList = buyService.selectReservation("SU_001");
        System.out.println(reservationVOList);

        if(subAccomCode==null||subAccomCode.equals(""))
        {
            model.addAttribute("SampleSubVO",buyService.selectSubAccom("SUB_001"));
        }else
        {
            model.addAttribute("SampleSubVO",buyService.selectSubAccom("subAccomCode"));
        }
        model.addAttribute("Reservationlist",reservationVOList);
        return "/buy/calendar";
    }
    @PostMapping("/buyBasket")
    public String addBaske(BasketAccomVO basketAccomVO, HttpSession session){
        System.out.println("111111111111111333333333333333333333333333333333333111111111111111");
        System.out.println(basketAccomVO);
        System.out.println( ((MemberVO)session.getAttribute("loginInfo")).getMemberId());
        basketAccomVO.setMemberId( ((MemberVO)session.getAttribute("loginInfo")).getMemberId());
        basketAccomVO.setStayStartDate(basketAccomVO.getStayStartDate().split("T")[0]+basketAccomVO.getStayStartDate().split("T")[1]);
        basketAccomVO.setStayEndDate(basketAccomVO.getStayEndDate().split("T")[0]+basketAccomVO.getStayEndDate().split("T")[1]);
        buyService.insertBasketAccom(basketAccomVO);
        return "/buy/buy_basket";
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
