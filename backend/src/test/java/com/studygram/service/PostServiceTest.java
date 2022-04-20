package com.studygram.service;

import com.studygram.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostServiceTest {

    PostService postService;

    @BeforeEach
    public void beforeEach() {
        postService = new PostService();
    }

    @Test
    void 게시판_작성() {
        //given
        Post post = new Post();
        post.setCommentId("test");
        post.setContent("test");
        post.setLikeId("test");
        post.setTagId("test");
        post.setImageUrlId("test");
        post.setUserId("test");

        //when
        int postIdx = postService.save(post);

        //then
        Post result = postService.findById(postIdx);
        assertThat(post.getIdx()).isEqualTo(result.getIdx());
        assertThat(post.getContent()).isEqualTo(result.getContent());
        assertThat(post.getUserId()).isEqualTo(result.getUserId());

    }
}
