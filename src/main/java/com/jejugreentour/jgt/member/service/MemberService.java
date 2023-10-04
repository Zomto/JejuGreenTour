package com.jejugreentour.jgt.member.service;

import com.jejugreentour.jgt.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    public int joinMember(MemberVO memberVO);

    //로그인
    public  MemberVO login(MemberVO memberVO);

//    //중복체크
//    public boolean checkId(String memberId);

}
