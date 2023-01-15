package com.studygram.service;

import com.studygram.utils.ApiKorToRoman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private SearchService searchService;

    @Test
    public void 한글영문변환() {
        ApiKorToRoman apiKorToRoman = new ApiKorToRoman();
        apiKorToRoman.convertLang("홍길동");
    }

    @Test
    public void 검색() {
        searchService.search("홍길동", 2);
    }


}


