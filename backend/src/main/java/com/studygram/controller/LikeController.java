package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Like;
import com.studygram.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(path="/save")
    public ApiResponse addLike(@RequestBody Like like) throws Exception {
        likeService.save(like);
        return ApiResponse.success(HttpStatus.OK.name(), like);
    }

}
