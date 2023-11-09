package com.jejugreentour.jgt.csCenter.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseVO {
    private String resCode;
    private String inqCode;
    private String inqResponse;
    private String resDate;
    private List<ResImgVO> resImgList;

}
