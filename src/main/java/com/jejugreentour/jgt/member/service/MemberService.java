package com.jejugreentour.jgt.member.service;

import com.jejugreentour.jgt.member.vo.MemberVO;

import java.util.List;

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
    // 아이디 찾기
    public List<MemberVO> findId(String member_mail);
    // 아이디, 이메일 일치여부확인
    public String checkInfo(MemberVO memberVO);
    //비밀번호 업데이트

    public void changePw(MemberVO memberVO);

    
}
