package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.User;
import com.studygram.mapper.LikeMapper;
import com.studygram.mapper.PostMapper;
import com.studygram.mapper.SearchMapper;
import com.studygram.mapper.UserMapper;
import com.studygram.utils.ApiKorToRoman;
import com.studygram.utils.ListComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    public List<?> search(String keyword, int type){
        switch(type) {
            case 1: {
                // 첫번째 : 키워드 포함한 게시글을 좋아요 순으로 정렬해서 조회하기(쿼리정렬 이용)
                return searchMapper.searchPostList(keyword);
                // 두번째 : 특정 키워드로 게시글 분류 -> 게시글 다시 조회 -> 좋아요 순으로 정렬(sort 이용)
                /*
                ArrayList<Post> postList = new ArrayList<>();
                List<Integer> pIdxList = searchMapper.searchPostList(keyword);
                for(int idx : pIdxList) {
                    postList.add(postMapper.findById(idx));
                }
                // Like 수로 정렬
                Collections.sort(postList, new ListComparator());
                return postList;
                 */
            }
            case 2: {
                // Accounts => 우선순위 : Followers
                // keyword 영어인 경우 로마자로 변경
                ApiKorToRoman api = new ApiKorToRoman();
                api.convertLang(keyword);
                return null;
//                return searchMapper.searchAccountList(keyword);
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
            case 3: {
                // Tag
                return searchMapper.searchTagList(keyword);
            }
            default: {
                break;
            }
        }
        return null;
    }

}
