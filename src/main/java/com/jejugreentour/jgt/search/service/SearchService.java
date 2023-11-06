package com.jejugreentour.jgt.search.service;


import com.jejugreentour.jgt.csCenter.vo.AnnVO;
import com.jejugreentour.jgt.csCenter.vo.QnaVO;
import com.jejugreentour.jgt.search.vo.SearchVO;

import java.util.List;

public interface SearchService {

    public List<AnnVO> searchAnn(SearchVO searchVO);

    public List<QnaVO> searchQna(SearchVO searchVO);
}
