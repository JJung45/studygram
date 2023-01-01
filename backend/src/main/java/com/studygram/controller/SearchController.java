package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.mapper.SearchMapper;
import com.studygram.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    // Enum 사용해서 Account / Tag / Post에서 내용에 Tag
    @Autowired
    private SearchService searchService;

    @GetMapping(path="")
    public void getKeyword(String keyword, int type) {
        searchService.search(keyword, type);
    }

}