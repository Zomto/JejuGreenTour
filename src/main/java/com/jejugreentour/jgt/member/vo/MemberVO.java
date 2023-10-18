package com.jejugreentour.jgt.member.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private String[] memberTels;
    private String gender;
    private String birthDate;
    private String memberRoll;
    private String member_mail;
}

