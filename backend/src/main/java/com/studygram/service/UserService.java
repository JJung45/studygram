package com.studygram.service;

import com.studygram.common.oauth.AuthTokenProvider;
import com.studygram.config.AppProperties;
import com.studygram.domain.User;
import com.studygram.mapper.UserMapper;
import com.studygram.mapper.UserRefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRefreshTokenMapper userRefreshTokenMapper;

    public User getUser(String userId) {
        return userMapper.findByUserName(userId);
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public void save(User user) {
        user.setPasswd(passwordEncoder.encode(user.getPasswd()));
        userMapper.save(user);
    }

}
