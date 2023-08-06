package com.studygram.mapper;

import com.studygram.domain.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    int save(Image image);

    void delete(Image image);

    Image findByPostIdx(int postIdx);

    String getStoredImagePathByPostIdx(int postIdx);
    
    int savePostImageRel(@Param("imageIdx")int imageIdx, @Param("postIdx")int postIdx);
    int saveUserImageRel(@Param("imageIdx")int imageIdx, @Param("userIdx")int userIdx);
    // 가장 최근에 저장된 프로필 사진 가져오기 (기본 이미지 외에 있을경우)
    String findRecentImageByUserIdx(int userIdx);
    String findRecentImageByUserName(String userName);
    String findRecentImageByClientId(String clientId);
    void deletePostImageRel(int imageIdx, int postIdx);

}
