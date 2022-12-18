package com.studygram.service;

import com.studygram.domain.Like;
import com.studygram.domain.User;
import com.studygram.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private UserService userService;

    public int save(Like like) {
        User user = userService.getUser();
        like.setUserId(user.getIdx());
        return likeMapper.save(like);
    }

    public int countAll() {
        return likeMapper.countAll();
    }

    public void delete(int postId) {
        User user = userService.getUser();
        Like like = Like.builder().userId(user.getIdx()).postId(postId).build();
        likeMapper.delete(like);
    }

    public Like randOneIdx() {
        return likeMapper.randOneIdx();
    }

    public Like findByPostUser(int postId, int userId) {
        return likeMapper.findByPostUser(postId, userId);
    }

    public boolean hasLikedPost(int postId, int userId) {
        return likeMapper.hasLikedPost(postId, userId);
    }

    public List<User> getLikers(int postId) {
        return likeMapper.findLikesByPostId(postId);
    }
}
