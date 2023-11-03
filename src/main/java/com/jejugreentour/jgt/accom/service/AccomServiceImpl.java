package com.jejugreentour.jgt.accom.service;

import com.jejugreentour.jgt.accom.vo.MainAccomImgVO;
import com.jejugreentour.jgt.accom.vo.MainAccomVO;
import com.jejugreentour.jgt.accom.vo.SubAccomImgVO;
import com.jejugreentour.jgt.accom.vo.SubAccomVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccomServiceImpl implements AccomService {
    private final SqlSessionTemplate sqlSession;


    @Override
    public String selectNextAccomCode() {
        return sqlSession.selectOne("accomMapper.selectNextAccomCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAccom(MainAccomVO mainAccomVO) {
        sqlSession.insert("accomMapper.addAccom", mainAccomVO);
        sqlSession.insert("accomMapper.insertImgs", mainAccomVO);
    }

    @Override
    public String selectNextsubAccomCode()  {
        return sqlSession.selectOne("accomMapper.selectNextsubAccomCode");
    }

    // 업소 상세 정보
    @Override
    public MainAccomVO selectMainAccomDetail(String accomCode) {
        return sqlSession.selectOne("accomMapper.selectMainAccomDetail", accomCode);
    }

    @Override
    public void updateMainAccomName(MainAccomVO mainAccomVO) {
        sqlSession.update("accomMapper.updateMainAccomName", mainAccomVO);
    }

    @Override
    public void updateMainAccomAddr(MainAccomVO mainAccomVO) {
        sqlSession.update("accomMapper.updateMainAccomAddr", mainAccomVO);
    }

    @Override
    public void updateMainAccomSubImg(MainAccomVO mainAccomVO) {
        sqlSession.insert("accomMapper.insertImgs", mainAccomVO);
    }

    @Override
    public void deleteSubImg(String mainImgCode) {
        sqlSession.delete("accomMapper.deleteSubImg", mainImgCode);
    }

    @Override
    public List<MainAccomImgVO> selectSubImg(String accomCode) {
        return  sqlSession.selectList("accomMapper.selectSubImg", accomCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSubAccom(SubAccomVO subAccomVO) {

        sqlSession.insert("accomMapper.insertSubaccom", subAccomVO);
        sqlSession.insert("accomMapper.insertSubImgs", subAccomVO);
    }

    @Override
    public List<SubAccomImgVO> selectSubAccomImg(String subAccomCode) {
        return  sqlSession.selectList("accomMapper.selectSubAccomImg", subAccomCode);
    }

    @Override
    public SubAccomVO selectSubAccomDetail(String subAccomCode) {
        return sqlSession.selectOne("accomMapper.selectSubAccomDetail", subAccomCode);
    }

    @Override
    public MainAccomVO test(MainAccomVO mainAccomVO) {
        return sqlSession.selectOne("accomMapper.test", mainAccomVO);
    }


}
