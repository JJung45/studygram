package com.studygram.service;

import com.studygram.domain.Post;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class PostServiceTest {

    // TODO 왜 beforeeach는 안될까..?
    @Autowired
    PostService postService;

    private int postCount;
    private Post originalPost;

    @Before
    public void beforeEach() {
        originalPost = new Post();
        originalPost.setContent("test");
        originalPost.setUserId(35);
        postService.save(originalPost);

        List<Post> posts = postService.findAll();
        postCount = posts.size();
    }

    @Test
    public void 게시판_작성() {
        //given
        Post post = new Post();
        post.setContent("test");
        post.setUserId(35);

        //when
        postService.save(post);

        //then
        Post resPost = postService.findById(post.getIdx());
        Assert.assertEquals(resPost.getIdx(), post.getIdx());
    }

    @Test
    public void 게시판_전체_조회() {
        //when
        Post post = new Post();
        post.setContent("test");
        post.setUserId(35);
        postService.save(post);

        //then
        List<Post> nowPosts = postService.findAll();
        Assert.assertEquals(nowPosts.size(), postCount + 1);
    }

    @Test
    public void 게시판_업데이트() {
        //given
        Post updatedPost = postService.findById(originalPost.getIdx());
        String beforeContent =updatedPost.getContent();

        //when
        updatedPost.setContent("hihi");
        postService.update(updatedPost);

        //then
        Post updatePost = postService.findById(updatedPost.getIdx());
        Assert.assertNotEquals(beforeContent,updatePost.getContent());
    }

    @Test
    public void 게시판_삭제() {
        //given

        //when
        Post newPost = postService.findById(originalPost.getIdx());
        Assert.assertNotNull(newPost);
        postService.delete(originalPost);

        //then
        Assert.assertNull(postService.findById(originalPost.getIdx()));
    }
}
