package com.studygram.service;

import com.studygram.domain.Follow;
import com.studygram.domain.User;
import com.studygram.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserService userService;

    public int follow(Follow follow)
    {
        if(userService.getUserInfo(follow.getToUserIdx()) == null)
            return 0;
        if(followMapper.getFollowInfo(follow) == null)
            return followMapper.saveFollow(follow);

        return 0;
    }

    public int unfollow(Follow follow)
    {
        if(userService.getUserInfo(follow.getToUserIdx()) == null)
            return 0;
        if(followMapper.getFollowInfo(follow) != null)
            return followMapper.saveUnfollow(follow);

        return 0;
    }

}
