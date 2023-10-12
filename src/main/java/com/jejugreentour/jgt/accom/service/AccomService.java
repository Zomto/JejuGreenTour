package com.jejugreentour.jgt.accom.service;

import com.jejugreentour.jgt.accom.vo.MainAccomVO;

public interface AccomService {
    public void addAccom(MainAccomVO mainAccomVO);

    // 다음 ITEM_CODE 조회
    public String selectNextAccomCode();
}
