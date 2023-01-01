package com.studygram.service;

import com.studygram.domain.*;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
public class PostServiceTest {

    private static final int userId = 24; //leehyeji
    @Autowired
    PostService postService;
    Post originalPost;
    int postCount;

    @Autowired
    CommentService commentService;
    Comment comment;

    @Autowired
    TagService tagService;
    Tag tag;

    @Autowired
    LikeService likeService;
    Like like;

    @Before
    public void beforeEach() {
        originalPost = new Post();
        originalPost.setContent("test");
        originalPost.setUserIdx(userId);
        postService.save(originalPost);

        comment = new Comment();
        comment.setContent("sdfsdf");
        comment.setUserIdx(originalPost.getUserIdx());
        comment.setPostIdx(originalPost.getIdx());
        commentService.createComment(comment);

        like = new Like();
        like.setUserIdx(originalPost.getUserIdx());
        like.setPostIdx(originalPost.getIdx());
        likeService.save(like);

        // TODO 데이터가 100개 넘었을 때 문제 발생할듯
        List<Post> posts = postService.findAll(100,1);
        postCount = posts.size();
    }

    @Test
    @WithMockUser(username = "112260294914450981060")
    public void 게시판_작성() {
        //given
        Post post = new Post();
        post.setContent("test2222 #태그입니다");
        post.setUserIdx(userId);

        List<Attachment> attachments = new ArrayList<>();
        attachments.add(new Attachment());
        post.setAttachedFiles(attachments);

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
        post.setContent("test22");
        post.setUserIdx(userId);
        postService.save(post);

        //then
        List<Post> nowPosts = postService.findAll(100,1);
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
        postService.delete(originalPost);

        //then
        Assert.assertNull(postService.findById(originalPost.getIdx()));
    }

    @Test
    public void 게시글_1개_조회() {
        // given
        int postId = 12;

        Post newPost = postService.findById(postId);
        System.out.println(newPost.toString());
    }

    @Test
    public void 좋아요_누른_게시글이_맞는지_확인() {
        /// given
        int likedUserId = 35;
        Like like = Like.builder()
                .userIdx(likedUserId)
                .postIdx(originalPost.getIdx())
                .build();

        // when
        likeService.save(like);

        // then
        assertTrue(postService.findByIds(originalPost.getIdx(), likedUserId).isHasLiked());
    }
}

