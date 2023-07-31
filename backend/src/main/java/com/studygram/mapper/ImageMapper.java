package com.studygram.mapper;

import com.studygram.domain.Image;
import com.studygram.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    int save(Image image);

    void delete(Image image);

    Image findByPostIdx(int postIdx);

    String getStoredImagePathByPostIdx(int postIdx);
    
    int savePostImage(int imageIdx, int postIdx);
    int saveUserImage(int imageIdx, int userIdx);
    // 가장 최근에 저장된 프로필 사진 가져오기 (기본 이미지 외에 있을경우)
    String findRecentImageByUserIdx(int userIdx);
}
