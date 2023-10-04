package com.jejugreentour.jgt.member.service;

import com.jejugreentour.jgt.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public int joinMember(MemberVO memberVO) {
        return sqlSession.insert("memberMapper.joinMember", memberVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }

//    @Override
//    public boolean checkId(String memberId) {
//        String selectMemberID = sqlSession.selectOne("memberMapper.checkId",memberId);
//        return selectMemberID == null ? true : false;
//    }
}
