package com.jejugreentour.jgt.buy.service;

import com.jejugreentour.jgt.buy.vo.BasketAccomVO;
import com.jejugreentour.jgt.buy.vo.ReservationVO;
import com.jejugreentour.jgt.buy.vo.SampleACCVO;
import com.jejugreentour.jgt.buy.vo.SampleSubVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

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
    public void insertReservation(BasketAccomVO basketAccomVO) {
        sqlSession.insert("buyMapper.insertReservation",basketAccomVO);
        sqlSession.delete("buyMapper.deleteBasketAccom",basketAccomVO);
    }

    @Override
    public ReservationVO selectReservationOne(String reservationCode) {
        return sqlSession.selectOne("buyMapper.selectReservationOne",reservationCode);
    }
}
