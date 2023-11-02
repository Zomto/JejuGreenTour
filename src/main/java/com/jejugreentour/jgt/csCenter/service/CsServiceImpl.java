package com.jejugreentour.jgt.csCenter.service;

import com.jejugreentour.jgt.csCenter.vo.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<AnnVO> annList(AnnVO annVO) {
        return sqlSession.selectList("csMapper.annList", annVO);
    }
    
    
    // 공지사항 목록 추가
    @Override
    public int insertAnn(AnnVO annVO) {
        return sqlSession.insert("csMapper.inputAnn", annVO);
    }

    // 공지사항 게시물  총 개수
    @Override
    public int selectAnnCnt(String annCate) {
        return sqlSession.selectOne("csMapper.selectAnnCnt", annCate);
    }

    // 공지사항 상세 조회
    @Override
    public AnnVO selectAnnDetail(AnnVO annVO) {
        return sqlSession.selectOne("csMapper.selectAnnDetail", annVO);
    }

    // 공지사항 카테고리 목록 조회
    @Override
    public List<AnnCateVO> annCateList(AnnCateVO annCateVO) {
        return sqlSession.selectList("csMapper.annCateList", annCateVO);
    }

    @Override
    public int updateAnn(AnnVO annVO) {
        return sqlSession.update("csMapper.updateAnn", annVO);
    }

    @Override
    public int deleteAnn(AnnVO annVO) {
        return sqlSession.delete("csMapper.deleteAnn", annVO);
    }

    // -----------------------------------------------------------------------------------------

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
        return sqlSession.insert("csMapper.insertQna", qnaVO);
    }

    // QNA 게시물 카운트
    @Override
    public int selectQnaCnt() {
        return sqlSession.selectOne("csMapper.selectQnaCnt");
    }

    // QNA 수정
    @Override
    public int updateQna(QnaVO qnaVO) {
        return sqlSession.update("csMapper.updateQna", qnaVO);
    }

    // QNA 삭제
    @Override
    public int deleteQna(String qnaCode) {
        return sqlSession.delete("csMapper.deleteQna", qnaCode);
    }


    // ------------------------------------------------------------------------------------------------
    
    // 문의하기 목록 조회
    @Override
    public List<InquireVO> InqList(InquireVO inquireVO) {
        return sqlSession.selectList("csMapper.inqList", inquireVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertInq(InquireVO inquireVO) {
        sqlSession.insert("csMapper.insertInq", inquireVO);
        sqlSession.insert("csMapper.insertInqImgs", inquireVO);

    }


    @Override
    public int selectInqCnt() {
        return sqlSession.selectOne("csMapper.selectInqCnt");
    }

    @Override
    public String nextInqCode() {
        return sqlSession.selectOne("csMapper.nextInqCode");
    }

    // 문의하기 세부 조회
    @Override
    public InquireVO inqDetail(String inqCode) {
        return sqlSession.selectOne("csMapper.inqDetail", inqCode);
    }

    @Override
    public List<InqImgVO> inqImgList(String inqCode) {
        return sqlSession.selectList("csMapper.inqImgList", inqCode);
    }


}
