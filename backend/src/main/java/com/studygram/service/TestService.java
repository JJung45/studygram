package com.studygram.service;

import com.studygram.dto.Test;
import com.studygram.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> selectAll() {
        return testMapper.selectAll();
    }
}
