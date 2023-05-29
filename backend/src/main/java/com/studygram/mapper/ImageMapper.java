package com.studygram.mapper;

import com.studygram.domain.Image;
import com.studygram.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    Long save(Image image);

    void delete(Image image);

    Image findByPostIdx(int postIdx);

    String getStoredImagePathByPostIdx(int postIdx);
}
