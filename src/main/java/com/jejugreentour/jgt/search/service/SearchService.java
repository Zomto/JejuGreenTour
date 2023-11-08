package com.jejugreentour.jgt.search.service;


import com.jejugreentour.jgt.csCenter.vo.AnnVO;
import com.jejugreentour.jgt.csCenter.vo.QnaVO;
import com.jejugreentour.jgt.search.vo.SearchVO;

import java.util.List;

public interface SearchService {

    // 검색 페이지 공지 사항
    public List<AnnVO> searchAnn(SearchVO searchVO);

    // 공지 사항 검색
    public List<AnnVO> searchAnnPaging(SearchVO searchVO);

    // 공지 사항 검색 게시물 카운트
    public int searchAnnCnt(SearchVO searchVO);



    // 검색 페이지 QNA
    public List<QnaVO> searchQna(SearchVO searchVO);

    // 고객 센터 QNA 검색
    public List<QnaVO> searchQnaPaging(SearchVO searchVO);

    // 고객 센터 QNA 게시물 카운트
    public int searchQnaCnt(SearchVO searchVO);




}
