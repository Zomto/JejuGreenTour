package com.jejugreentour.jgt.csCenter.service;

import com.jejugreentour.jgt.csCenter.vo.AnnCateVO;
import com.jejugreentour.jgt.csCenter.vo.AnnVO;
import com.jejugreentour.jgt.csCenter.vo.InquireVO;
import com.jejugreentour.jgt.csCenter.vo.QnaVO;

import java.util.List;

public interface CsService {


    // 고객센터 메인 페이지 공지사항 목록 조회
    public List<AnnVO> annListOffset();

    // 공지사항 목록 조회
    public List<AnnVO> annList(AnnVO annVO);

    // 공지사항 목록 추가
    public int insertAnn(AnnVO annVO);
    
    // 공지사항 게시물 총 개수 조회
    public int selectAnnCnt();
    
    // 공지사항 상세 조회
    public AnnVO selectAnnDetail(AnnVO annVO);

    // 공지사항 메뉴 목록 조회
    public List<AnnCateVO> annCateList(AnnCateVO annCateVO);


    // --------------------------------------------------------------------------------

    // 고객센터 메인 페이지 QNA 목록 조회
    public List<QnaVO> qnaListOffset();

    // QNA 목록 조회
    public List<QnaVO> qnaList(QnaVO qnaVO);

    // QNA 목록 추가
    public int insertQna(QnaVO qnaVO);
    
    // 게시물 총 개수 조회
    public int selectQnaCnt();

    // ---------------------------------------------------

    // 문의 내역 목록 조회
    public List<InquireVO> InqList(InquireVO inquireVO);

    // 게시물 총 개수 조회
    public int selectInqCnt();




}
