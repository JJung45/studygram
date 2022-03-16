package com.studygram.mapper;

import com.studygram.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User findByUserId(String userId);
    User findByEmailId(String emailId);
    User saveAndFlush(User user);

}
