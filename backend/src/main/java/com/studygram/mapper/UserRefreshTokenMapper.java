package com.studygram.mapper;

import com.studygram.domain.UserRefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRefreshTokenMapper {
    // userId 값에 user_name 또는 email_idx 또는 phone_num 가 들어올 수 있음.
    UserRefreshToken findByClientId(String clientId);
    UserRefreshToken findByClientIdAndRefreshToken(@Param("clientId")String clientId, @Param("refreshToken")String refreshToken);

    int updateRefreshToken(UserRefreshToken userRefreshToken);

    int saveAndFlush(UserRefreshToken userRefreshToken);
}
