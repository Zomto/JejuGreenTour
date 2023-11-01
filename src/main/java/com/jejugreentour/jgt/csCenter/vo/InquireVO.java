package com.jejugreentour.jgt.csCenter.vo;

import lombok.Data;

import java.util.List;

@Data
public class InquireVO extends PageVO{
    private String inqCode;
    private String cateCode;
    private String memberId;
    private String title;
    private String content;
    private String inqDate;
    private String isResponse;
    private InqCateVO inqCateList;
    private List<InqImgVO> inqImgList;
}
