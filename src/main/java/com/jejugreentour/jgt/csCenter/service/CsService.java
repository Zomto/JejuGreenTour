package com.jejugreentour.jgt.csCenter.service;

import com.jejugreentour.jgt.csCenter.vo.*;

import java.util.List;

public interface CsService {


    // 고객센터 메인 페이지 공지사항 목록 조회
    public List<AnnVO> annListOffset();

    // 공지사항 목록 조회
    public List<AnnVO> annList(AnnVO annVO);

    // 공지사항 목록 추가
    public int insertAnn(AnnVO annVO);
    
    // 공지사항 게시물 총 개수 조회
    public int selectAnnCnt(String annCate);
    
    // 공지사항 상세 조회
    public AnnVO selectAnnDetail(AnnVO annVO);

    // 공지사항 메뉴 목록 조회
    public List<AnnCateVO> annCateList(AnnCateVO annCateVO);
    
    // 공지사항 수정
    public int updateAnn(AnnVO annVO);

    // 공지사항 삭제
    public int deleteAnn(AnnVO annVO);


    // --------------------------------------------------------------------------------

    // 고객센터 메인 페이지 QNA 목록 조회
    public List<QnaVO> qnaListOffset();

    // QNA 목록 조회
    public List<QnaVO> qnaList(QnaVO qnaVO);

    // QNA 목록 추가
    public int insertQna(QnaVO qnaVO);
    
    // 게시물 총 개수 조회
    public int selectQnaCnt();

    // QNA 수정
    public int updateQna(QnaVO qnaVO);

    // QNA 삭제
    public int deleteQna(String qnaCode);

    // ---------------------------------------------------

    // 문의 내역 목록 조회
    public List<InquireVO> InqList(InquireVO inquireVO);

    // 문의 내역 목록 추가
    public void insertInq(InquireVO inquireVO);

    // 문의 내역 총 개수 조회
    public int selectInqCnt();

    // 다음 문의 코드 조회
    public String nextInqCode();

    // 문의 내역 세부 조회
    public InquireVO inqDetail(String inqCode);

    // 문의 내역 사진 조회
    public List<InqImgVO> inqImgList(String inqCode);



}
