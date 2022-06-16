package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    @GetMapping(path="/{tagId}")
    public Tag getTag(@PathVariable(name = "tagId") int tagId) throws Exception{
        //TODO
        return new Tag();
    }

    @PostMapping(path = "/save")
    public ApiResponse addTag(@RequestBody Tag tag) throws Exception{
        //TODO
        return ApiResponse.success(HttpStatus.OK.name(), tag);
    }

    @DeleteMapping(path="/{postId}")
    public void deleteTag(@PathVariable(name="tagId") int tagId) throws Exception {
        //TODO

    }
}
