package com.jejugreentour.jgt.search.vo;

import com.jejugreentour.jgt.csCenter.vo.AnnCateVO;
import lombok.Data;

@Data
public class AnnSearchVO {
    private String searchAnnCate;
    private String searchAnnWriter;
    private String searchAnnTitle;
    private String searchAnnContent;
    private AnnCateVO searchAnnCateList;
}
