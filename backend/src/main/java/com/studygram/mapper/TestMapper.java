package com.studygram.mapper;

import com.studygram.dto.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Test> selectAll();
}
