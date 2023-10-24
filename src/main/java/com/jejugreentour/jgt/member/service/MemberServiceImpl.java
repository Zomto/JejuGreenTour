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
    public int join(MemberVO memberVO) {
        return sqlSession.insert("memberMapper.join", memberVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }

    @Override
    public boolean checkId(String memberId) {
        String selectMemberID = sqlSession.selectOne("memberMapper.checkId",memberId);
        System.out.println(selectMemberID);
        return selectMemberID == null ? true : false;
    }

    @Override
    public int updateMember1(MemberVO memberVO) {
        return sqlSession.update("memberMapper.updateMember1", memberVO);
    }

    @Override
    public int updateMember2(MemberVO memberVO) {
        System.out.println(memberVO);
        return sqlSession.update("memberMapper.updateMember2", memberVO);
    }
}
