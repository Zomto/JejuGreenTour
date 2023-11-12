package com.jejugreentour.jgt.accom.vo;

import com.jejugreentour.jgt.csCenter.vo.PageVO;
import lombok.Data;

import java.util.List;

@Data
public class MainAccomVO extends PageVO {
    private String accomCode;
    private String accomName;
    private String accomAddr;
    private String accomIntro;
    private String accomCate;
    private String accomCeo;
    private String accomLoc;
    private String addrDetail;
    private List<MainAccomImgVO> mainAccomImgList;
    private AccomCategoryVO accomCategory;
    private String inputMainAccomName;
    private String inputMainAccomIntro;
    private String inputAccomAddr;
    private String inputAddrDetail;
}