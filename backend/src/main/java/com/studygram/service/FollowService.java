package com.studygram.service;

import com.studygram.domain.Follow;
import com.studygram.domain.User;
import com.studygram.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserService userService;

    public int follow(Follow follow)
    {
        int fromUserIdx = setMyIdx();
        if(follow.getToUserIdx() == fromUserIdx)
            return 0;
        follow.setFromUserIdx(fromUserIdx);
        System.out.println("Follow: "+ follow.toString());
        if(userService.getUserInfo(follow.getToUserIdx()) == null)
            return 0;

        if(followMapper.getFollowInfo(follow) == null)
            return followMapper.saveFollow(follow);

        return 0;
    }

    public int unfollow(Follow follow)
    {
        int fromUserIdx = setMyIdx();
        if(follow.getToUserIdx() == fromUserIdx)
            return 0;
        follow.setFromUserIdx(fromUserIdx);
        if(userService.getUserInfo(follow.getToUserIdx()) == null)
            return 0;

        if(followMapper.getFollowInfo(follow) != null)
            return followMapper.saveUnfollow(follow);

        return 0;
    }

    public int setMyIdx() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String clientID = userDetails.getUsername();
        return userService.getUser(clientID).getIdx();
    }
}
