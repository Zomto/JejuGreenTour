package com.jejugreentour.jgt.buy.vo;

import lombok.Data;

@Data
public class BasketAccomVO {
    private String basketCode;
    private String subAccomCode;
    private String memberId;
    private String stayStartDate;
    private String stayEndDate;
    private String stayTerm;
    private String dayuse;
}
