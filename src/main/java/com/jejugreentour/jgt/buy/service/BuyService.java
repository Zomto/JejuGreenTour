package com.jejugreentour.jgt.buy.service;

import com.jejugreentour.jgt.buy.vo.BasketAccomVO;
import com.jejugreentour.jgt.buy.vo.ReservationVO;
import com.jejugreentour.jgt.buy.vo.SampleACCVO;
import com.jejugreentour.jgt.buy.vo.SampleSubVO;
import com.jejugreentour.jgt.member.vo.MemberVO;

import java.util.List;

public interface BuyService {


    //여관 정보가져오기
    public SampleACCVO selectAccom(String accomCode);

    public SampleSubVO selectSubAccom(String subAccomCode);


    //실제로 해당 방의 예약된 날짜 정보를 조회
    public List<ReservationVO> selectReservation(String subAccomCode);
    //장바구니에 필요한 정보를 추가
    public void insertBasketAccom(BasketAccomVO basketAccomVO);
    //장바구니 리스트 조회
    public List<BasketAccomVO> selectBasketAccomList(String memberId);
    //선택한 예약 결제
    public void insertReservation(BasketAccomVO basketAccomVO);

    //결제 취소 admin 시점 user 시점 (환불금액 차이)

}
