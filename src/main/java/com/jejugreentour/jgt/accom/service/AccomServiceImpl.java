package com.jejugreentour.jgt.accom.service;

import com.jejugreentour.jgt.accom.vo.MainAccomVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccomServiceImpl implements AccomService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public int addAccom(MainAccomVO mainAccomVO) {
        return sqlSession.insert("accomMapper.addAcomm", mainAccomVO);
    }

    @Override
    public String selectNextAccomCode() {
        return sqlSession.selectOne("accomMapper.selectNextAccomCode");
    }
}
