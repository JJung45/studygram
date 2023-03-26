package com.studygram.mapper;

import com.studygram.domain.UserRefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRefreshTokenMapper {
    Integer findUserIdxByClientId(String clientId);
    UserRefreshToken findByClientId(String clientId);
    UserRefreshToken findByClientIdAndRefreshToken(@Param("clientId")String clientId, @Param("refreshToken")String refreshToken);

    int updateRefreshToken(UserRefreshToken userRefreshToken);

    int saveAndFlush(UserRefreshToken userRefreshToken);

}
