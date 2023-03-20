package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Follow;
import com.studygram.domain.User;
import com.studygram.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    // GetMapping (PathVariable)
    // PostMapping (@RequestParam)
    @PostMapping("/follow")
    public ApiResponse follow(@RequestBody Follow follow)
    {
        if(followService.follow(follow) > 0)
            return ApiResponse.success(HttpStatus.OK.name(), null);
        else
//            return ApiResponse.fail();
            return ApiResponse.alreadyFollowed();
    }

    @DeleteMapping("/unfollow")
    public ApiResponse unfollow(@RequestParam int toUserIdx)
    {
        if(followService.unfollow(toUserIdx) > 0)
            return ApiResponse.success(HttpStatus.OK.name(), null);
        else
            return ApiResponse.fail();
    }

    @GetMapping("/followChk")
    public boolean checkFollow(@RequestParam int toUserIdx) {
        return followService.checkFollow(toUserIdx);
    }

    @GetMapping("/suggestions")
    public List<User> suggestions(@RequestParam int userIdx)
    {
        // 계정추천
        return followService.suggestAccounts(userIdx);
    }

}
