package com.jejugreentour.jgt.member.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private String[] memberTels;
    private String memberAddr;
    private String addrDetail;
    private String memberEmail;
    private String[] memberEmails;
    private String gender;
    private String memberRoll;
}

