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

        // 팔로우 하는 사람 계정 정보 유무 확인
        if(userService.getUserInfo(follow.getToUserIdx()) == null)
            return 0;

        // 팔로우 이미 되어있는지 확인 => 없으면 return
        if(followMapper.getFollowInfo(follow) == null)
            return followMapper.saveFollow(follow);

        System.out.println("Already Followed User");
        return 0;
    }

    public int unfollow(int toUserIdx)
    {
        int fromUserIdx = setMyIdx();
        if(toUserIdx == fromUserIdx)
            return 0;

        if(userService.getUserInfo(toUserIdx) == null)
            return 0;

        Follow follow = new Follow();
        follow.setToUserIdx(toUserIdx);
        follow.setFromUserIdx(fromUserIdx);
        if(followMapper.getFollowInfo(follow) != null)
            return followMapper.saveUnfollow(follow);

        return 0;
    }

    public int testFollow(Follow follow)
    {
        if(followMapper.getFollowInfo(follow) == null) {
            return followMapper.saveFollow(follow);
        }
        return 0;
    }

    public boolean checkFollow(int toUserIdx)
    {
        int fromUserIdx = setMyIdx();
        Follow follow = new Follow();
        follow.setToUserIdx(toUserIdx);
        follow.setFromUserIdx(fromUserIdx);
        // 이미 팔로우 한 상태라면 true
        if(followMapper.getFollowInfo(follow) != null)
            return true;
        else
            return false;
    }

    public int setMyIdx() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String clientID = userDetails.getUsername();
        return userService.getUser(clientID).getIdx();
    }
}
