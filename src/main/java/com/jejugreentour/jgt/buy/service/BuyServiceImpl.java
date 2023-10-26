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
}
