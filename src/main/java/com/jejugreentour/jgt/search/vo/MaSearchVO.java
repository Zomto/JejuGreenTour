package com.jejugreentour.jgt.search.vo;

import com.jejugreentour.jgt.accom.vo.AccomCategoryVO;
import lombok.Data;

@Data
public class MaSearchVO {
    private String searchAccomName;
    private String searchAccomAddr;
    private String searchAddrDetail;
    private String searchAccomCate;
    private String searchAccomLoc;
    private AccomCategoryVO searchAccomCategory;
}
