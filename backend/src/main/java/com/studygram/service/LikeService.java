package com.studygram.service;

import com.studygram.domain.Like;
import com.studygram.domain.NotificationType;
import com.studygram.domain.User;
import com.studygram.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likeMapper;

    private final UserService userService;

    private final NotificationService notificationService;

    public void save(Like like) {
        User user = userService.getUser();
        like.setUserIdx(user.getIdx());
        likeMapper.save(like);
        notificationService.send(user.getIdx(), user.getIdx(), like.getIdx(), NotificationType.LIKE);
    }

    public int countAll() {
        return likeMapper.countAll();
    }

    public void delete(int postIdx) {
        User user = userService.getUser();
        Like like = Like.builder().userIdx(user.getIdx()).postIdx(postIdx).build();
        likeMapper.delete(like);
    }

    public Like randOneIdx() {
        return likeMapper.randOneIdx();
    }

    public Like findByPostUser(int postIdx, int userIdx) {
        return likeMapper.findByPostUser(postIdx, userIdx);
    }

    public boolean hasLikedPost(int postIdx, int userIdx) {
        return likeMapper.hasLikedPost(postIdx, userIdx);
    }

    public List<User> getLikers(int postIdx) {
        return likeMapper.findLikersByPostIdx(postIdx);
    }
}
