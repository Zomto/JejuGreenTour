package com.jejugreentour.jgt.buy.service;

import com.jejugreentour.jgt.buy.vo.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BuyServiceImpl implements BuyService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public SampleACCVO selectAccom(String accomCode) {
        return sqlSession.selectOne("buyMapper.selectAccom",accomCode);
    }

    @Override
    public SampleSubVO selectSubAccom(String subAccomCode) {
        return sqlSession.selectOne("buyMapper.selectSubAccom",subAccomCode);
    }

    @Override
    public String selectNewbasketNum() {
        return sqlSession.selectOne("buyMapper.basketNum");
    }
    @Override
    public String selectNewreservationNum() {
        return sqlSession.selectOne("buyMapper.reservationNum");
    }

    @Override
    public Boolean selectbasketAccom(String basketCode) {
        return sqlSession.selectOne("buyMapper.selectbasketAccom", basketCode)==null;
    }

    @Override
    public List<ReservationVO> selectReservation(String subAccomCode) {
       return sqlSession.selectList("buyMapper.selectReservation",subAccomCode);
    }

    @Override
    public void insertBasketAccom(BasketAccomVO basketAccomVO) {
        sqlSession.insert("buyMapper.insertBasketAccom",basketAccomVO);
    }

    @Override
    public List<BasketAccomVO> selectBasketAccomList(String memberId) {
        return sqlSession.selectList("buyMapper.selectBasketAccomList",memberId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertReservation(BasketAccomVO basketAccomVO) {
        sqlSession.insert("buyMapper.insertReservation",basketAccomVO);
        ReservationStateVO vo=new ReservationStateVO();
        vo.setCanRefundDate(basketAccomVO.getStayStartDate().split("T")[0]);
        vo.setOverDate(basketAccomVO.getStayEndDate().split("T")[0]);
        vo.setReservationCode(basketAccomVO.getReservUuid());
        sqlSession.insert("buyMapper.insertReservationstate",vo);
        sqlSession.delete("buyMapper.deleteBasketAccom",basketAccomVO);
    }

    @Override
    public void deleteBasketAccom(String basketCode) {
        sqlSession.delete("buyMapper.deleteBasketAccom",basketCode);
    }

    @Override
    public ReservationVO selectReservationOne(String reservationCode) {
        return sqlSession.selectOne("buyMapper.selectReservationOne",reservationCode);
    }

    @Override
    public List<ReservationVO> selectMemberReservationList(String memberId) {
        return sqlSession.selectList("buyMapper.selectMemberReservationList",memberId);
    }

    @Override
    public int updateReservationstate(ReservationStateVO stateVO) {
        return sqlSession.update("buyMapper.updateReservationstate",stateVO);
    }

    @Override
    public String selectReviewCode() {
        return sqlSession.selectOne("buyMapper.selectReviewCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertReview(ReviewVO reviewVO) {
        sqlSession.insert("buyMapper.insertReview",reviewVO);
        sqlSession.insert("buyMapper.insertReviewImg",reviewVO);
    }

    @Override
    public List<ReviewVO> accomReviewList(String accomCode) {
        return sqlSession.selectList("buyMapper.accomReviewList",accomCode);
    }

    @Override
    public List<ReviewVO> memberReviewList(String memberId) {
        return sqlSession.selectList("buyMapper.memberReviewList",memberId);
    }

    @Override
    public ReviewVO selectReviewOne(String reservattonCode) {
        return sqlSession.selectOne("buyMapper.selectReviewOne",reservattonCode);
    }

    @Override
    public int deleteReview(String reviewCode) {
        sqlSession.delete("buyMapper.deleteReviewImg",reviewCode);
        sqlSession.delete("buyMapper.deleteAdminReview",reviewCode);
        return sqlSession.delete("buyMapper.deleteReview",reviewCode);
    }

    @Override
    public List<ReviewImgVO> selectReviewImgList(String reviewCode) {
        return sqlSession.selectList("buyMapper.selectReviewImgList",reviewCode);
    }

    @Override
    public void deleteReviewImg(ReviewVO reviewVO) {
        sqlSession.delete("buyMapper.deleteReviewImg",reviewVO);
    }

    @Override
    public void ReInsertReviewImg(ReviewVO reviewVO) {
        sqlSession.insert("buyMapper.insertReviewImg",reviewVO);
    }

    @Override
    public void updateReview(ReviewVO reviewVO) {
       sqlSession.update("buyMapper.updateReview",reviewVO);
    }

    @Override
    public int insertAdminReview(ReviewAdminVO reviewAdminVO) {
        return sqlSession.insert("buyMapper.insertAdminReview",reviewAdminVO);
    }

    @Override
    public int deleteAdminReview(ReviewAdminVO reviewAdminVO) {
        return sqlSession.delete("buyMapper.deleteAdminReview",reviewAdminVO);
    }

    @Override
    public void insertPlan(ReservationVO reservationVO) {
         sqlSession.insert("buyMapper.insertPlan",reservationVO);
    }

    @Override
    public List<PlanVO> selectPlan(String reservationCode) {
        return sqlSession.selectList("buyMapper.selectPlan",reservationCode);
    }
}
