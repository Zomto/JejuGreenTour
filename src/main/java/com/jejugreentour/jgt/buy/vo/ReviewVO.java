package com.jejugreentour.jgt.buy.vo;

import lombok.Data;

import java.util.List;

@Data
public class ReviewVO {
    private String reviewCode;
    private String accomCode;
    private String memberId;
    private String reservationCode;
    private String score;
    private String content;
    private String writeDate;
    private SampleACCVO sampleACCVO;
    private ReviewAdminVO reviewAdminVO;
    private List<ReviewImgVO> reviewImgList;
}
