package com.studygram.service;

import com.studygram.domain.Comment;
import com.studygram.domain.Like;
import com.studygram.domain.Post;
import com.studygram.domain.Tag;
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

    private static final int userId = 5;

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
        originalPost.setUserId(userId);
        postService.save(originalPost);

        comment = new Comment();
        comment.setContent("sdfsdf");
        comment.setUserId(originalPost.getUserId());
        comment.setPostId(originalPost.getIdx());
        commentService.createComment(comment);

        tag = new Tag();
        tag.setContent("sdf");
        tag.setPostId(originalPost.getIdx());
        tagService.save(tag);

        like = new Like();
        like.setUserId(originalPost.getUserId());
        like.setPostId(originalPost.getIdx());
        likeService.save(like);

        // TODO 데이터가 100개 넘었을 때 문제 발생할듯
        List<Post> posts = postService.findAll(100,1);
        postCount = posts.size();
    }

    @Test
    public void 게시판_작성() {
        //given
        Post post = new Post();
        post.setContent("test");
        post.setUserId(userId);

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
        post.setUserId(userId);
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
        Post newPost = postService.findById(originalPost.getIdx());
        Assert.assertNotNull(newPost);
        postService.delete(originalPost);

        //then
        Assert.assertNull(postService.findById(originalPost.getIdx()));
    }
}
