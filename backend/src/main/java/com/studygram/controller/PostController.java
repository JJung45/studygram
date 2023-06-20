package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Post;
import com.studygram.service.ImageUploadService;
import com.studygram.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    public static final int LIMIT = 100;
    public static final int OFFSET = 1;

    @Autowired
    private PostService postService;

    @PostMapping(path = "/save")
    public ApiResponse<Post> addPost(HttpServletRequest request,
                               @RequestParam(value="fileImage") MultipartFile file, Post post) throws Exception {
        Post newPost = postService.save(post, file);
        return ApiResponse.success(HttpStatus.OK.name(), newPost);
    }

    // TODO @PathVariable(name="postIdx")?
    @PutMapping(path = "/{postIdx}")
    public ApiResponse updatePost(@PathVariable(name="postIdx") @RequestBody Post post) throws Exception{
        Post newPost = postService.update(post);
        return ApiResponse.success(HttpStatus.OK.name(), newPost);
    }

    @DeleteMapping(path="/{postIdx}")
    public void deletePost(@PathVariable(name="postIdx") int postIdx) throws Exception {
        Post post = postService.findById(postIdx);
        postService.delete(post);
    }

    @GetMapping(path="/{postIdx}")
    public Post getPost(@PathVariable(name = "postIdx") int postIdx) throws Exception{
        return postService.findById(postIdx);
    }

    @GetMapping(path = "/")
    public List<Post> getPosts(@RequestParam @Nullable Integer limit, @RequestParam @Nullable Integer offset) throws Exception {
        // TODO 사진 연동 필요
        if (limit == null) {
            limit = LIMIT;
        }

        if (offset == null) {
            offset = OFFSET;
        }

        return postService.findAll(limit, offset);
    }

    @GetMapping(path = "/myPage/{userName}")
    public List<Post> getPostsByClientId(@PathVariable(name="userName") String userName, @RequestParam @Nullable Integer limit, @RequestParam @Nullable Integer offset) {
        if(limit == null) {
            limit = LIMIT;
        }

        if(offset == null) {
            offset = OFFSET;
        }

        return postService.findByUserName(userName);
    }

}
