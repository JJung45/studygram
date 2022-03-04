package com.studygram.service;

import com.studygram.domain.User;
import com.studygram.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String userId) {
        if (userId.contains("@")) {
            return userMapper.findByEmailId(userId);
        } else {
            return userMapper.findByUserId(userId);
        }
    }
}
