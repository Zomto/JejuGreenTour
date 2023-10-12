package com.jejugreentour.jgt.accom.service;

import com.jejugreentour.jgt.accom.vo.MainAccomVO;

public interface AccomService {
    public void addAccom(MainAccomVO mainAccomVO);

    // 다음 ITEM_CODE 조회
    public String selectNextAccomCode();

    // 업소 상세 조회
    public MainAccomVO selectMainAccomDetail(String accomCode);

    // 업소 이름 변경
    public void updateMainAccomName(MainAccomVO mainAccomVO);
}
