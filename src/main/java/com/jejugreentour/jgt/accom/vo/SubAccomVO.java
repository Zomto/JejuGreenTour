package com.jejugreentour.jgt.accom.vo;

import lombok.Data;

@Data
public class SubAccomVO {
    private String subAccomCode;
    private String accomCode;
    private String subAccomName;
    private String subAccomIntro;
    private String subAccomCate;
    private String accomPrice;
    private String basicMan;
    private String maxMan;
    private String accomStartTime;
    private String accomEndTime;
    private String rentTime;
    private String soldOut;
}
