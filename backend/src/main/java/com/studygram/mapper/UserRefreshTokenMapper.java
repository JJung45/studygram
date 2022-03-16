package com.studygram.mapper;

import com.studygram.domain.UserRefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRefreshTokenMapper {
    UserRefreshToken findByUserId(String userId);
    UserRefreshToken findByUserIdAndRefreshToken(@Param("userId")String userId, @Param("refreshToken")String refreshToken);

    UserRefreshToken findByEmailId(String emailId);
    UserRefreshToken findByEmailIdAndRefreshToken(@Param("emailId")String emailId, @Param("refreshToken")String refreshToken);

    int updateRefreshToken(UserRefreshToken userRefreshToken);

    int saveAndFlush(UserRefreshToken userRefreshToken);
}
