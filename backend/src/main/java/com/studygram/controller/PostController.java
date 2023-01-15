package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Attachment;
import com.studygram.domain.AttachmentType;
import com.studygram.domain.Post;
import com.studygram.service.AttachmentService;
import com.studygram.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    public static final int LIMIT = 100;
    public static final int OFFSET = 1;


    @Autowired
    private PostService postService;

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping(path = "/save")
    public ApiResponse addPost(@RequestBody Post post) throws Exception{
        Post postWithAttachment = createPostAttachment(post);
        postService.save(postWithAttachment);
        return ApiResponse.success(HttpStatus.OK.name(), post);
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

    private Post createPostAttachment(Post post)
    {
        try {
            Map<AttachmentType, File> files = attachmentService.getAttachmentTypeListMap(post.getFileImage());
            List<Attachment> attachments = attachmentService.saveAttachments(files);
            attachments.stream()
                    .forEach(attachment -> post.setAttachedFiles(attachments));

            return post;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
