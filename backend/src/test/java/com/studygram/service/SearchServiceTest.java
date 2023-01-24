package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.utils.ApiKorToRoman;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private SearchService searchService;
    String keyword = null;

    @Before
    public void before() {
        keyword = "i95";
    }

    @Test
    public void 한글영문변환() throws ParseException {
        ApiKorToRoman apiKorToRoman = new ApiKorToRoman();
        apiKorToRoman.convertLang("게시글");
    }

    @Test
    public void 검색() throws ParseException {
        System.out.println("게시글 검색: " + searchService.search(keyword,1));
        System.out.println("계정 검색: " + searchService.search(keyword,2));
        System.out.println("태그 검색: " + searchService.search(keyword,3));
    }


}


