package com.studygram.mapper;

import com.studygram.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LikeMapper {
    int save(Like like);
    void delete(Like like);
    int countAll();
    Like randOneIdx();
}
