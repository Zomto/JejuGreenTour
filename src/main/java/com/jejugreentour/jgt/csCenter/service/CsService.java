package com.jejugreentour.jgt.csCenter.service;

import com.jejugreentour.jgt.csCenter.vo.AnnVO;

import java.util.List;

public interface CsService {
    
    // 공지사항 목록 조회
    public List<AnnVO> annList();
    
    
    // 공지사항 목록 추가
    public int insertAnn(AnnVO annVO);
}
