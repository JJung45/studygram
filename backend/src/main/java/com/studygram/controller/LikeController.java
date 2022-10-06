package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Like;
import com.studygram.domain.Post;
import com.studygram.domain.User;
import com.studygram.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(path="/{postId}")
    public void deleteLike(@PathVariable(name="postId") int postId) throws Exception {
        // TODO Session get userId
        Like like = likeService.findByPostUser(postId, 28);
        likeService.delete(like);
    }

}
