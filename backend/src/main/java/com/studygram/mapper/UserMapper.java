package com.studygram.mapper;

import com.studygram.domain.User;

public interface UserMapper {
    User findByUserId(String userId);
    User findByEmailId(String emailId);
    User saveAndFlush(User user);

}
