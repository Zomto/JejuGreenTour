package com.jejugreentour.jgt.member.service;

import com.jejugreentour.jgt.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    public int join(MemberVO memberVO);

    //로그인
    public  MemberVO login(MemberVO memberVO);

    //중복체크
    public boolean checkId(String memberId);

    //회원 정보수정(이메일, 전화번호 = 1)
    public int updateMember1(MemberVO memberVO);
    //회원 정보수정(비밀번호 = 2)
    public int updateMember2(MemberVO memberVO);
}
