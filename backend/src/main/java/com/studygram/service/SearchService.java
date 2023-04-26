package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.SearchResultObject;
import com.studygram.domain.Tag;
import com.studygram.domain.User;
import com.studygram.mapper.LikeMapper;
import com.studygram.mapper.PostMapper;
import com.studygram.mapper.SearchMapper;
import com.studygram.mapper.UserMapper;
import com.studygram.utils.ApiKorToRoman;
import com.studygram.utils.ListComparator;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class SearchService {
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserService userService;

    // 검색
    // 1. 기본으로 검색하면 프로필 메시지 & account 에서 검색
    // 2. 검색 누르면 세분화 -> 게시글, 태그, 계정
    // 수정 -> 검색하면 태그 + 계정 검색결과로 조회
    // 선택한 태그로 다시 게시물 검색
    public List<?> search(String keyword, int type) throws ParseException {
        switch (type) {
            case 1: {
                // Accounts
                String tmp = null;
                if (Pattern.matches("^[ㄱ-ㅎ가-힣]*$", keyword)) {
                    // 한글 포함
                    // keyword 한글인 경우 영어 로마자로 변경
                    ApiKorToRoman api = new ApiKorToRoman();
                    tmp = api.convertLang(keyword);
                } else {
                    System.out.println("한글인명(" + keyword + ") 영문자 변환 없음");
                }
                return searchMapper.searchAccountList(keyword, tmp);
                // 2번째 방법
                /*
                ArrayList<User> userList = new ArrayList<>();
                List<Integer> uIdxList = searchMapper.searchAccountList(keyword);
                for(int idx : uIdxList)
                    userList.add(userService.getUserInfo(idx));

                // Follower 수로 정렬
                Collections.sort(userList, new ListComparator());
                return userList;
                 */
            }
            case 2: {
                // Tag
                return searchMapper.searchTagList(keyword);
            }
            default: {
                System.out.println("잘못된 검색 타입");
                break;
            }
        }
        return null;
    }

    public SearchResultObject search(String keyword) throws ParseException {
        SearchResultObject searchResultObject = new SearchResultObject();
        List<Tag> tags = searchMapper.searchTagList(keyword);

        String tmp = null;
        if (Pattern.matches("^[ㄱ-ㅎ가-힣]*$", keyword)) {
            // 한글 포함
            // keyword 한글인 경우 영어 로마자로 변경
            ApiKorToRoman api = new ApiKorToRoman();
            tmp = api.convertLang(keyword);
        } else {
            System.out.println("한글인명(" + keyword + ") 영문자 변환 없음");
        }
        List<User> users = searchMapper.searchAccountList(keyword, tmp);

        searchResultObject.setTagList(searchMapper.searchTagList(keyword));
        searchResultObject.setUserList(searchMapper.searchAccountList(keyword, tmp));
        return searchResultObject;
    }

}
