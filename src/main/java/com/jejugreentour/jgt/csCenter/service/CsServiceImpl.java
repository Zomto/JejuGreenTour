package com.jejugreentour.jgt.csCenter.service;

import com.jejugreentour.jgt.csCenter.vo.AnnVO;
import com.jejugreentour.jgt.csCenter.vo.QnaVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsServiceImpl implements CsService{
    private final SqlSessionTemplate sqlSession;

    // 고객센터 메인 페이지 공지사항 목록 조회
    @Override
    public List<AnnVO> annListOffset() {
        return sqlSession.selectList("csMapper.annListOffset");
    }

    // 공지사항 목록 조회
    @Override
    public List<AnnVO> annList() {
        return sqlSession.selectList("csMapper.annList");
    }
    
    
    // 공지사항 목록 추가
    @Override
    public int insertAnn(AnnVO annVO) {
        return sqlSession.insert("csMapper.inputAnn", annVO);
    }

    // 고객센터 메인 페이지 QNA 목록 조회
    @Override
    public List<QnaVO> qnaListOffset() {
        return sqlSession.selectList("csMapper.qnaListOffset");
    }

    // QNA 목록 조회
    @Override
    public List<QnaVO> qnaList(QnaVO qnaVO) {
        return sqlSession.selectList("csMapper.qnaList", qnaVO);
    }

    // QNA 목록 추가
    @Override
    public int insertQna(QnaVO qnaVO) {
        return sqlSession.insert("csMapper.inputQna", qnaVO);
    }

    @Override
    public int selectQnaCnt() {
        return sqlSession.selectOne("csMapper.selectQnaCnt");
    }
}
