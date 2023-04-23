package com.studygram.service;

import com.studygram.domain.*;
import com.studygram.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    public Post save(Post post) {
        User user = userService.getUser();
        post.setUserIdx(user.getIdx());
        postMapper.save(post);

        List<String> tagList = tagService.saveTags(post);

        for(String tagContent : tagList) {
            String newContent = post.getContent().replace(tagContent, "<a link=''>"+tagContent+"</a>");
            post.setContent(newContent);

            postMapper.update(post);
        }

        return post;
    }

    public Post findById(int postIdx) {
        User user = userService.getUser();
        return postMapper.findByIds(postIdx, user.getIdx());
    }

    public Post findByIds(int postIdx, int userIdx) {
        return postMapper.findByIds(postIdx, userIdx);
    }

    public List<Post> findByClientId() {
        User user = userService.getUser();
        return postMapper.findByUserInfo(user.getClientId(), null);
    }

    public List<Post> findByUserName(String userName) {
        return postMapper.findByUserInfo(null, userName);
    }

    public List<Post> findAll(Integer limit, Integer offset) {
        User user = userService.getUser(); // TODO 해야함
        Integer userIdx = (user == null ? null : user.getIdx());
        return postMapper.findAll(limit, offset, userIdx);
    }

    public Post update(Post post) {
        // TODO 댓글, 좋아요, 태그 연결해서 업데이트
        postMapper.update(post);
        return post;
    }

    public void delete(Post post) {
        postMapper.delete(post);
    }
}
