package com.jejugreentour.jgt.accom.vo;

import lombok.Data;

@Data
public class AccomReviewVO {
    private String accomCode;
    private Double reviewPoint;
    private String reviewDetail;
    private String originFileName;
    private String attachedFileName;
    private String reviewDate;
}
