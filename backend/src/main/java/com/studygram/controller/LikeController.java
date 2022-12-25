package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Like;
import com.studygram.domain.User;
import com.studygram.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(path = "/save")
    public ApiResponse save(@RequestBody Like like) throws Exception {
        likeService.save(like);
        return ApiResponse.success(HttpStatus.OK.name(), like);
    }

    @DeleteMapping(path = "/{postId}")
    public void cancle(@PathVariable(name = "postId") int postId) {
        likeService.delete(postId);
    }

    @GetMapping(path = "/{postId}")
    public List<User> getLikers(@PathVariable(name = "postId") int postId) {
        return likeService.getLikers(postId);
    }
}
