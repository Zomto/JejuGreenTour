package com.jejugreentour.jgt.buy.vo;

import lombok.Data;

@Data
public class ReservationStateVO {
    private String reservationStateCode;
    private String reservationCode;
    private String refund; //Y or N
    private String review; //Y or N
    private String refundPrice;
    private String refundDate;// DEFAULT NULL
    private String canRefundDate;
    private String overDate;
}
