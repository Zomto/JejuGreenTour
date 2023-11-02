package com.jejugreentour.jgt.buy.service;

import com.jejugreentour.jgt.buy.vo.*;
import com.jejugreentour.jgt.member.vo.MemberVO;

import java.util.List;

public interface BuyService {


    //여관 정보가져오기
    public SampleACCVO selectAccom(String accomCode);

    public SampleSubVO selectSubAccom(String subAccomCode);

    public String selectNewbasketNum();
    public String selectNewreservationNum();
    public Boolean selectbasketAccom(String basketCode);


    //실제로 해당 방의 예약된 날짜 정보를 조회
    public List<ReservationVO> selectReservation(String subAccomCode);
    //장바구니에 필요한 정보를 추가
    public void insertBasketAccom(BasketAccomVO basketAccomVO);
    //장바구니 리스트 조회
    public List<BasketAccomVO> selectBasketAccomList(String memberId);
    //선택한 예약 결제
    public void insertReservation(BasketAccomVO basketAccomVO);
    //바스켓 삭제
    public void deleteBasketAccom(String basketCode);


    // 결제완료 환인
    public ReservationVO selectReservationOne(String reservationCode);

    // 결제상태 리스트
    public List<ReservationVO> selectMemberReservationList(String memberId);

    // 환불
    public int updateReservationstate(ReservationStateVO stateVO);

    //리뷰 코드가져오기
    public String selectReviewCode();

    //이미지 리뷰 넣기
    public void insertReview(ReviewVO reviewVO);

    //숙박시설 리뷰조회
    public List<ReviewVO> accomReviewList(String accomCode);
    //회원이 쓴 리뷰조회
    public List<ReviewVO> memberReviewList(String memberId);

    //리뷰 수정을 위한 조회
    public ReviewVO selectReviewOne(String reservattonCode);

    public int deleteReview(String reviewCode);

    //리뷰 이미지 가져오기
    public  List<ReviewImgVO> selectReviewImgList(String reviewCode);


    //리뷰이미지 지우기
    public  void  deleteReviewImg(ReviewVO reviewVO);

    //리뷰이미지 다시 넣기
    public  void  ReInsertReviewImg(ReviewVO reviewVO);

    //리뷰업데이트
    public  void  updateReview(ReviewVO reviewVO);

    //숙박업소주인 답변달기
    public int insertAdminReview(ReviewAdminVO reviewAdminVO);

    //숙박업소주인 답볍삭제
    public int deleteAdminReview(ReviewAdminVO reviewAdminVO);


    //결제 취소 admin 시점 user 시점 (환불금액 차이)

}
