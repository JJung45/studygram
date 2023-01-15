package com.studygram.mapper;

import com.studygram.domain.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    Long save(Image image);
}
