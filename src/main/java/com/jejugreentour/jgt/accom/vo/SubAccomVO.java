package com.jejugreentour.jgt.accom.vo;

import lombok.Data;

import java.util.List;

@Data
public class SubAccomVO {
    private String subAccomCode;
    private String accomCode;
    private String subAccomName;
    private String subAccomIntro;
    private String subAccomCate;
    private List<String> subAccomCates;//mapper 수정
    private String dayusePrice;  //mapper 수정
    private String reservationPrice;  //mapper 수정
    private String basicMan;
    private String maxMan;
    private String accomStartTime;
    private String accomEndTime;
    private String rentTime;
    private String soldOut;
    private String dayuseCan;//mapper 수정
    private List<SubAccomImgVO> subAccomImgList;
}
