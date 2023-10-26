package com.jejugreentour.jgt.csCenter.vo;

import lombok.Data;

import java.util.List;

@Data
public class AnnVO extends PageVO{
    private String annNum;
    private String annCate;
    private String annWriter;
    private String annTitle;
    private String annContent;
    private String annDate;
    private String memberId;
    private AnnCateVO annCateList;
}
