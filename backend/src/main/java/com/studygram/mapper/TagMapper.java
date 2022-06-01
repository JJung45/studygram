package com.studygram.mapper;

import com.studygram.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TagMapper {
    int save(Tag tag);
}
