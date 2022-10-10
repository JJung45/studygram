package com.studygram.service;

import com.studygram.domain.Like;
import com.studygram.domain.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class LikeServiceTest {

    private static final int userId = 5;

    @Autowired
    PostService postService;
    Post originalPost;

    @Autowired
    LikeService likeService;
    int likeCount;

    @Before
    public void before() {
        originalPost = new Post();
        originalPost.setContent("test");
        originalPost.setUserId(userId);
        postService.save(originalPost);

        // TODO likeservice count 제작
        likeCount = likeService.countAll();
    }

    @Test
    public void 좋아요_저장() {
        Like like = new Like();
        like.setUserId(originalPost.getUserId());
        like.setPostId(originalPost.getIdx());
        likeService.save(like);

        int newLikeCount = likeService.countAll();

        Assert.assertEquals(newLikeCount, likeCount + 1);
    }

    @Test
    public void 좋아요_삭제() {
        // 랜덤 제거
        // assertEqual 제거한 수 , likeCount-1
        Like deletedLike = likeService.randOneIdx();
        likeService.delete(deletedLike);

        int newLikeCount = likeService.countAll();

        Assert.assertEquals(newLikeCount, likeCount - 1);
    }

    @Test
    public void 좋아요_누른_게시글이_맞는지_확인()
    {
        // given
        int likedUserId = 35;
        Like like = Like.builder()
                .userId(likedUserId)
                .postId(originalPost.getIdx())
                .build();

        // when
        likeService.save(like);

        // then
        assertTrue(likeService.hasLikedPost(originalPost.getIdx(), likedUserId));
    }
}
