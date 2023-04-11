package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.mapper.SearchMapper;
import com.studygram.service.PostTagService;
import com.studygram.service.SearchService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private PostTagService postTagService;

    @GetMapping(path="")
    public ApiResponse getKeyword(@RequestParam String keyword) throws ParseException {
//    public ApiResponse getKeyword(@RequestParam String keyword, @RequestParam int type) throws ParseException {
//        List<?> searchList = searchService.search(keyword, type);
//        return ApiResponse.success("searchList", searchList);
        return ApiResponse.success("searchList", searchService.search(keyword));
    }

    @GetMapping(path="")
    public ApiResponse getPostsByTagIdx(@RequestParam int tagIdx, @RequestParam int ordering) {
        return ApiResponse.success("postList", postTagService.findPostByTagIdx(tagIdx, ordering));
    }


}
