package com.jejugreentour.jgt.buy.vo;

import lombok.Data;

import java.util.List;

@Data
public class SampleSubVO {
    private String subAccomCode;
    private String accomCode;
    private String subAccomName;
    private String accomPrice;
    private String dayusePrice;
    private String accomStartTime;
    private String accomEndTime;
    private String rentTime;
    private String dayuseCan;
    private List<ReservationVO> ReservationList;
}
