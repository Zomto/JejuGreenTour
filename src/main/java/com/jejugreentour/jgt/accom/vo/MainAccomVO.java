package com.jejugreentour.jgt.accom.vo;

import lombok.Data;

import java.util.List;

@Data
public class MainAccomVO {
    private String accomCode;
    private String accomName;
    private String accomAddr;
    private String accomIntro;
    private String accomCate;
    private String accomCeo;
    private String accomLoc;
    private String latitude;
    private String longitude;
    private String addrDetail;
    private List<MainAccomImgVO> mainAccomImgList;
}