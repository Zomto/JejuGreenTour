package com.jejugreentour.jgt.buy.vo;

import lombok.Data;

import java.util.List;

@Data
public class ReviewVO {
    private String reviewCode;
    private String accomCode;
    private String memberId;
    private String score;
    private String content;
    private String writeDate;
    private List<ReviewImgVO> reviewImgList;
}
