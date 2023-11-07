package com.jejugreentour.jgt.csCenter.vo;

import com.jejugreentour.jgt.search.vo.SearchVO;
import lombok.Data;

@Data
public class QnaVO extends PageVO {
    private String qnaCode;
    private String cateCode;
    private String qnaQuestion;
    private String qnaAnswer;
    private QnaCateVO qnaCateList;
}
