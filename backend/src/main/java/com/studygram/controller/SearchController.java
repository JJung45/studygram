package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.mapper.SearchMapper;
import com.studygram.service.PostTagService;
import com.studygram.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private PostTagService postTagService;

    @GetMapping(path="/v1")
    public ApiResponse getKeyword(@RequestParam String keyword, @RequestParam int type) throws ParseException {
        return ApiResponse.success("searchList", searchService.search(keyword, type));
    }

    @GetMapping(path="/v2")
    public ApiResponse getKeyword(@RequestParam String keyword) throws ParseException {
        return ApiResponse.success("searchList", searchService.search(keyword));
    }

    @GetMapping(path="/post")
    public ApiResponse getPostsByTagIdx(@RequestParam int tagIdx, @RequestParam int ordering) {
        return ApiResponse.success("postList", postTagService.findPostByTagIdx(tagIdx, ordering));
    }


}
