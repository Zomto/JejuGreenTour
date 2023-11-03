package com.jejugreentour.jgt.accom.service;

import com.jejugreentour.jgt.accom.vo.MainAccomImgVO;
import com.jejugreentour.jgt.accom.vo.MainAccomVO;
import com.jejugreentour.jgt.accom.vo.SubAccomImgVO;
import com.jejugreentour.jgt.accom.vo.SubAccomVO;

import java.util.List;

public interface AccomService {
    public void addAccom(MainAccomVO mainAccomVO);

    // 다음 subAccomCode 조회
    public String selectNextsubAccomCode();

    // 다음 AccomCode 조회
    public String selectNextAccomCode();

    // 업소 상세 조회
    public MainAccomVO selectMainAccomDetail(String accomCode);

    // 업소 이름 변경
    public void updateMainAccomName(MainAccomVO mainAccomVO);

    // 업소 주소 변경
    public void updateMainAccomAddr(MainAccomVO mainAccomVO);

    // 서브 사진 추가
    public void updateMainAccomSubImg(MainAccomVO mainAccomVO);

    // 서브 사진 삭제
    public void deleteSubImg(String mainImgCode);

    // 서브 사진 불러오기
    public List<MainAccomImgVO> selectSubImg(String accomCode);

    // 서브 업소 등록
    public void addSubAccom(SubAccomVO subAccomVO);

    // 숙박 방 사진 불러오기
    public List<SubAccomImgVO> selectSubAccomImg(String subAccomCode);

    // 숙박 방 상세 조회
    public SubAccomVO selectSubAccomDetail(String subAccomCode);
}
