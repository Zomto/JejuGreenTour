package com.jejugreentour.jgt.csCenter.service;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CsServiceImpl implements CsService{
    private final SqlSessionTemplate sqlSession;

}
