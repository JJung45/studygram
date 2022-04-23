package com.studygram.service;

import com.studygram.domain.Post;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// TODO 왜 spirngboottest intializationError가 날까..?
@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceTest {

    // TODO 왜 beforeeach는 안될까..?
    @Autowired
    PostService postService;

    @Test
    void 게시판_작성() {
        //given
        Post post = new Post();
        post.setCommentsId(1);
        post.setContent("test");
        post.setLikesId(1);
        post.setTagsId(3);
        post.setImageUrlId(4);
        post.setUserId(35);

        //when
        int postIdx = postService.save(post);

        System.out.println(postIdx);

    }
}
