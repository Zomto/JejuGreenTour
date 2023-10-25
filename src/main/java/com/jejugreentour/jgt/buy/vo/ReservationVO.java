package com.jejugreentour.jgt.buy.vo;

import lombok.Data;

@Data
public class ReservationVO {
    private String reservationCode;
    private String subAccomCode;
    private String memberId;
    private String stayStartDate;
    private String stayEndDate;
    private String reservationDate;
    private String reservationPrice;
    private String reservationName;
    private String dayuse;
    private ReservationStateVO stateVO;
    private SampleSubVO subAccom;
}
