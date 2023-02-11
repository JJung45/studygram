package com.studygram.service;

import com.studygram.common.oauth.AuthTokenProvider;
import com.studygram.config.AppProperties;
import com.studygram.domain.User;
import com.studygram.mapper.FollowMapper;
import com.studygram.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private FollowMapper followMapper;

    public User getUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return getUser(username);
    }

    public User getUser(String clientId) {
        return userMapper.findByClientId(clientId);
    }

    public User getUserInfo() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return userMapper.findInfoByClientId(username);
    }

    public User getUserInfo(String userName) {
        return userMapper.findInfoByUserName(userName);
    }

    public User getUserInfo(int userIdx) {
        User userInfo = userMapper.findByUserIdx(userIdx);
        userInfo.setFollowingCnt(followMapper.countFollowings(userIdx));
        userInfo.setFollowing(followMapper.getFollowings(userIdx));
        userInfo.setFollowersCnt(followMapper.countFollowers(userIdx));
        userInfo.setFollowers(followMapper.getFollowers(userIdx));
        return userInfo;
    }

    public User getClientId(String clientId) {
        return userMapper.findByClientId(clientId);
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public void save(User user) {
        user.setPasswd(passwordEncoder.encode(user.getPasswd()));
        userMapper.save(user);
    }

}
