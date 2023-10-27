package com.jejugreentour.jgt.csCenter.vo;

public class PageVO {
    private int nowPage; // 현재 페이지 번호
    private int totalDataCnt; // 전체 데이터 수
    private int displayDataCnt; // 한 페이지에 보여지는 데이터 수
    private int beginPage; // 화면에 보이는 첫번째 페이지 번호
    private int endPage; // 화면에 보이는 마지막 페이지 번호
    private int displayPageCnt; // 한 번에 보여지는 페이지 수
    private boolean prev; // 이전 버튼의 유무
    private boolean next; // 다음 버튼의 유무

    public PageVO(){
        nowPage = 1;
        displayPageCnt = 5;
        displayDataCnt = 10;

    }

    // 페이징 처리를 하기 위한 모든 변수의 값을 세팅하는 메소드
    public void setPageInfo(){
        endPage = (int)Math.ceil(nowPage / (double)displayPageCnt) * displayPageCnt;
        beginPage = endPage - displayPageCnt + 1;

        // 전체 페이지 수
        int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayDataCnt);

        // next 버튼 유무
        if(endPage < totalPageCnt){
            next = true;
        } else {
            next = false;
            endPage = totalPageCnt;
        }

        // 이전 버튼 유무
        if(beginPage == 1){
            prev = false;
        } else {
            prev = true;
        }



    }

    // 전체 데이터 수 setter (게시글의 개수가 계속 변하기 때문에 초기값을 잡을수가 없음)
    public void setTotalDataCnt(int totalDataCnt){
        this.totalDataCnt = totalDataCnt;
    }

    // 현재 페이지 getter
    public int getNowPage(){
        return nowPage;
    }

    public void setNowPage(int nowPage){
        this.nowPage = nowPage;
    }

    // displayDataCnt getter
    public int getDisplayDataCnt(){
        return displayDataCnt;
    }

    public int getBeginPage(){
        return beginPage;
    }

    public int getEndPage(){
        return endPage;
    }

    public boolean getPrev(){
        return prev;
    }

    public boolean getNext(){
        return next;
    }

    public int getTotalDataCnt(){
        return totalDataCnt;
    }
}
