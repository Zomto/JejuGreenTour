package com.jejugreentour.jgt.search.service;

import com.jejugreentour.jgt.accom.vo.MainAccomVO;
import com.jejugreentour.jgt.accom.vo.SubAccomVO;
import com.jejugreentour.jgt.csCenter.vo.AnnVO;
import com.jejugreentour.jgt.csCenter.vo.QnaVO;
import com.jejugreentour.jgt.search.vo.SearchVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<AnnVO> searchAnn(SearchVO searchVO) {
        return sqlSession.selectList("csMapper.searchAnn", searchVO);
    }

    @Override
    public List<AnnVO> searchAnnPaging(SearchVO searchVO) {
        return sqlSession.selectList("csMapper.searchAnnPaging", searchVO);
    }

    @Override
    public int searchAnnCnt(SearchVO searchVO) {
        return sqlSession.selectOne("csMapper.searchAnnCnt", searchVO);
    }

    @Override
    public List<QnaVO> searchQna(SearchVO searchVO) {
        return sqlSession.selectList("csMapper.searchQna", searchVO);
    }

    @Override
    public List<QnaVO> searchQnaPaging(SearchVO searchVO) {
        return sqlSession.selectList("csMapper.searchQnaPaging", searchVO);
    }

    @Override
    public int searchQnaCnt(SearchVO searchVO) {
        return sqlSession.selectOne("csMapper.searchQnaCnt", searchVO);
    }

    @Override
    public List<MainAccomVO> searchAccom(SearchVO searchVO) {
        return sqlSession.selectList("accomMapper.searchAccom", searchVO);
    }

    @Override
    public List<SubAccomVO> searchSubAccom(SearchVO searchVO) {
        return sqlSession.selectList("accomMapper.searchSubAccom", searchVO);
    }

}
