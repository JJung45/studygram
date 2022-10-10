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
            return ApiResponse.fail();
    }

    @PostMapping("/unfollow")
    public ApiResponse unfollow(@RequestBody Follow follow)
    {
        if(followService.unfollow(follow) > 0)
            return ApiResponse.success(HttpStatus.OK.name(), null);
        else
            return ApiResponse.fail();
    }



}
