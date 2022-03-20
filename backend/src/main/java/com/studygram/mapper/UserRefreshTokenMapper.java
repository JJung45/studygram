package com.studygram.mapper;

import com.studygram.domain.UserRefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRefreshTokenMapper {
    // userId 값에 user_name 또는 email_id 또는 phone_id 가 들어올 수 있음.
    UserRefreshToken findByUserId(String userId);
    UserRefreshToken findByUserIdAndRefreshToken(@Param("userId")String userId, @Param("refreshToken")String refreshToken);

    int updateRefreshToken(UserRefreshToken userRefreshToken);

    int saveAndFlush(UserRefreshToken userRefreshToken);
}
