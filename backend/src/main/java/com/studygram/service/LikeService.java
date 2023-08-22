package com.studygram.service;

import com.studygram.domain.Like;
import com.studygram.domain.NotificationType;
import com.studygram.domain.Post;
import com.studygram.domain.User;
import com.studygram.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likeMapper;

    private final UserService userService;

    private final NotificationService notificationService;

    private final PostService postService;

    public void save(Like like) {
        // UserID 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUser(userDetails.getUsername());

        like.setUserIdx(user.getIdx());
        likeMapper.save(like);

        Post post = postService.findById(like.getPostIdx());
        int toUserIdx = post.getUserIdx();

        notificationService.send(toUserIdx, user.getIdx(), like.getIdx(), NotificationType.LIKE);
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
