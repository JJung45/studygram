package com.studygram.mapper;

import com.studygram.domain.Member;
import com.studygram.domain.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Test> selectAll();
    List<Member> selectMemberAll();
}
