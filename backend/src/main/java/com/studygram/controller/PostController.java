package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Post;
import com.studygram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    public static final int LIMIT = 100;
    public static final int OFFSET = 1;


    @Autowired
    private PostService postService;

    @PostMapping(path = "/save")
    public ApiResponse addPost(Post post) throws Exception{
        postService.save(post);
        return ApiResponse.success(HttpStatus.OK.name(), post);
    }

    // TODO @PathVariable(name="postId")?
    @PutMapping(path = "/{postId}")
    public ApiResponse updatePost(@PathVariable(name="postId") @RequestBody Post post) throws Exception{
        Post newPost = postService.update(post);
        return ApiResponse.success(HttpStatus.OK.name(), newPost);
    }

    @DeleteMapping(path="/{postId}")
    public void deletePost(@PathVariable(name="postId") int postId) throws Exception {
        Post post = postService.findById(postId);
        postService.delete(post);
    }

    @GetMapping(path="/{postId}")
    public Post getPost(@PathVariable(name = "postId") int postId) throws Exception{
        return postService.findById(postId);
    }

    @GetMapping(path = "/")
    public List<Post> getPosts(@RequestParam @Nullable Integer limit, @RequestParam @Nullable Integer offset) throws Exception{
        // TODO 사진 연동 필요
        if(limit == null) {
            limit = LIMIT;
        }

        if(offset == null) {
            offset = OFFSET;
        }
        
        return postService.findAll(limit, offset);
    }

}
