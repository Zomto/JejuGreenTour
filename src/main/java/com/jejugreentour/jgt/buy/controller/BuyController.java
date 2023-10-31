package com.jejugreentour.jgt.buy.controller;

import com.jejugreentour.jgt.accom.vo.SubAccomVO;
import com.jejugreentour.jgt.buy.service.BuyService;
import com.jejugreentour.jgt.buy.vo.*;
import com.jejugreentour.jgt.member.vo.MemberVO;
import com.jejugreentour.jgt.util.UploadReviewUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping({"/buy"})
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;

    @GetMapping("/calendar")
    public String calendar(Model model ,String subAccomCode){


        List<ReservationVO> reservationVOList ;
        if(subAccomCode==null||subAccomCode.equals(""))
        {
            model.addAttribute("SampleSubVO",buyService.selectSubAccom("SUB_001"));
            reservationVOList = buyService.selectReservation("SUB_001");
        }else
        {
            model.addAttribute("SampleSubVO",buyService.selectSubAccom(subAccomCode));
            reservationVOList = buyService.selectReservation(subAccomCode);
        }

        model.addAttribute("Reservationlist",reservationVOList);
        return "/content/buy/calendar";
    }
    @ResponseBody
    @PostMapping("/setBasketCode")
    public String setBasketCode(){
        return buyService.selectNewbasketNum();
    }

    @PostMapping("/buyBasket")
    public String addBaske(BasketAccomVO basketAccomVO, HttpSession session, Model model){

        SampleSubVO subVO=buyService.selectSubAccom(basketAccomVO.getSubAccomCode());
        model.addAttribute("subAccomCode",subVO);
        model.addAttribute("AccomCode",buyService.selectAccom(subVO.getAccomCode()));

        basketAccomVO.setMemberId( ((MemberVO)session.getAttribute("loginInfo")).getMemberId());

        System.out.println("ddddddddddddddddddddddddddddddddddddddddddd");
        System.out.println(basketAccomVO.getBasketCode());
        if(basketAccomVO.getBasketCode().equals(buyService.selectNewbasketNum())){
            buyService.insertBasketAccom(basketAccomVO);
        }

        return "/content/buy/buy_basket";
    }
    @PostMapping("/accomReservation")
    public String accomReservation(BasketAccomVO basketAccomVO, Model model){

        if(buyService.selectbasketAccom(basketAccomVO.getBasketCode())){ //다른 체크 함수 제작시 옮김
            return "redirect:/buy/calendar";
        }
        String randid= UUID.randomUUID().toString().split("-")[0].toUpperCase();
        basketAccomVO.setReservUuid(randid);
        buyService.insertReservation(basketAccomVO);
        ReservationVO reservationVO= buyService.selectReservationOne(randid);
        model.addAttribute("reservation",reservationVO);
        return "/content/buy/buy_reservation";
    }

    @GetMapping("/basketList")
    public String basketList(Model model,HttpSession session){
        List<BasketAccomVO> list=buyService.selectBasketAccomList(((MemberVO)session.getAttribute("loginInfo")).getMemberId());
        System.out.println(list);
        model.addAttribute("basketList",list);
        return "/content/buy/basket_list";
    }

    @GetMapping("/deleteBasketAccom")
    public String deleteBasketAccom(String basketCode){
        buyService.deleteBasketAccom(basketCode);
     return "redirect:/buy/basketList";
    }

    @ResponseBody
    @PostMapping("/reservationList") // 결제가능 여부 비교를 위해
    public List<ReservationVO> reservationList(String subAccomCode){
        return  buyService.selectReservation(subAccomCode);
    }

    @GetMapping("/reservList") // 결제리스트 조회
    public String reservList(HttpSession session, Model model){
        MemberVO memberVO=(MemberVO)session.getAttribute("loginInfo") ;
        List<ReservationVO> list= buyService.selectMemberReservationList(memberVO.getMemberId());
        model.addAttribute("Reservationlist",list);
        return  "/content/buy/reservation_list";
    }

    @ResponseBody
    @PostMapping("/refund")
    public int refundReservation(ReservationStateVO stateVO){
        System.out.println(stateVO);
        return  buyService.updateReservationstate(stateVO);
    }

    @GetMapping("/reviewReset")
    public String reviewReset( ReservationVO reservationVO, HttpSession session, Model model){

        reservationVO= buyService.selectReservationOne(reservationVO.getReservationCode());
        SampleSubVO subVO = buyService.selectSubAccom(reservationVO.getSubAccomCode());
        subVO.setSampleACCVO(buyService.selectAccom(subVO.getAccomCode()));
        ReviewVO reviewVO =buyService.selectReviewOne(reservationVO.getReservationCode());

        reviewVO.setReviewImgList(buyService.selectReviewImgList(reviewVO.getReviewCode()));

        System.out.println("333333333333333333333333333333333333333333333");
        System.out.println(reviewVO.getReviewImgList().toString());
        reservationVO.setSubAccom(subVO);
        model.addAttribute("review",reviewVO);
        model.addAttribute("reservation",reservationVO);

        return"/content/buy/review";
    }

    @GetMapping("/review")
    public  String reviewWrite(ReservationVO reservationVO, HttpSession session, Model model){

        reservationVO= buyService.selectReservationOne(reservationVO.getReservationCode());
//        if(session.getAttribute("loginInfo")!=null&&reservationVO.getMemberId().equals(((MemberVO)session.getAttribute("loginInfo")).getMemberId())){
//            return"/buy/review";
//        }else {
//             return "redirect:/";
//        }
        SampleSubVO subVO = buyService.selectSubAccom(reservationVO.getSubAccomCode());
        subVO.setSampleACCVO(buyService.selectAccom(subVO.getAccomCode()));
        reservationVO.setSubAccom(subVO);
        model.addAttribute("reservation",reservationVO);

        return"/content/buy/review";
    }



    @PostMapping("/insertReview")
    public String insertReview(ReviewVO reviewVO,String[] reviewImgCode, MultipartFile[] imgs){

        if(reviewVO.getReviewCode()==null||reviewVO.getReviewCode().equals("")){
            String reviewCode= buyService.selectReviewCode();
            List<ReviewImgVO> imgList = UploadReviewUtil.multiFileUpload(imgs);
            if (!imgList.isEmpty()) {
                for (ReviewImgVO imgVO : imgList) {
                    imgVO.setReviewCode(reviewCode);
                }
            }else {
                imgList = new ArrayList<>();
            }
            reviewVO.setReviewImgList(imgList);
            reviewVO.setReviewCode(reviewCode);
            buyService.insertReview(reviewVO);

            ReservationStateVO stateVO=new ReservationStateVO();
            stateVO.setReview("Y");
            stateVO.setRefund("N");
            stateVO.setRefundPrice("0");
            stateVO.setReservationCode(reviewVO.getReservationCode());
            buyService.updateReservationstate(stateVO);
        }else {

           if (imgs[0].isEmpty()){
               buyService.updateReview(reviewVO);
           }else {
            reviewVO.setReviewImgList(new ArrayList<>());
               for (String ImgCode:reviewImgCode) {
                   ReviewImgVO r=new ReviewImgVO();
                   r.setReviewCode(ImgCode);
                   reviewVO.getReviewImgList().add(r);
               }
               buyService.deleteReviewImg(reviewVO);
               List<ReviewImgVO> imgList = UploadReviewUtil.multiFileUpload(imgs);
               if (!imgList.isEmpty()) {
                   for (ReviewImgVO imgVO : imgList) {
                       imgVO.setReviewCode(reviewVO.getReviewCode());
                   }
               }else {
                   imgList = new ArrayList<>();
               }
               reviewVO.setReviewImgList(imgList);
               reviewVO.setReviewCode(reviewVO.getReviewCode());
               buyService.ReInsertReviewImg(reviewVO);
               buyService.updateReview(reviewVO);

           }
           System.out.println(Arrays.toString(reviewImgCode));
           System.out.println(reviewVO);
       }
        return "redirect:/buy/reservList";
    }

    //손님이 작성한 리뷰리스트
    @GetMapping("/reviewList")
    public  String reviewList(HttpSession session ,Model model){
        MemberVO vo =(MemberVO)session.getAttribute("loginInfo");
        System.out.println(buyService.memberReviewList(vo.getMemberId()));
        model.addAttribute("myReviewList",buyService.memberReviewList(vo.getMemberId()));

        return"/content/buy/review_list";
    }
    
    //주인이 보는 리뷰리스트 답변추가
    @GetMapping("/accomReviewList")
    public  String reviewList(SubAccomVO subAccomVO,Model model){
        System.out.println(buyService.accomReviewList(subAccomVO.getAccomCode()));
        model.addAttribute("myReviewList",buyService.accomReviewList(subAccomVO.getAccomCode()));

        return"/content/buy/accom_review_list";
    }

    @ResponseBody
    @PostMapping("/adminReply")
    public int adminReply(ReviewAdminVO reviewAdminVO, HttpSession session){
        MemberVO vo =(MemberVO)session.getAttribute("loginInfo");
        reviewAdminVO.setMemberId(vo.getMemberId());
        System.out.println(reviewAdminVO);
        return  buyService.insertAdminReview(reviewAdminVO);
    }


    @GetMapping("/adminCalendar")
    public String adminCalendar() {

        return"/content/buy/admin_calendar";
    }

}

