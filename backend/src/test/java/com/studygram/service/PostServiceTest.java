package com.studygram.service;

import com.studygram.domain.*;
import com.studygram.mapper.*;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
public class PostServiceTest {

    private static final int userIdx = 24; //leehyeji
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

    @Autowired
    UserService userService;

    @Autowired
    PostMapper postMapper;

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    private AmazonS3ResourceStorage amazonS3ResourceStorage;

    private MockMultipartFile mockImage;

    @SneakyThrows
    @Before
    public void beforeEach() {

        mockImage = new MockMultipartFile(
                "image",
                "image.jpg",
                "image/jpeg",
                new byte[0]
        );

        originalPost = new Post();
        originalPost.setContent("test");
        originalPost.setUserIdx(userIdx);

        postService.save(originalPost, mockImage);

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
    @WithMockUser(username = "108915067662391092609")
    public void 게시판_작성() {
        //given
        Post post = new Post();
        post.setContent("test2222 #태그입니다");
        post.setUserIdx(userIdx);

        //when
        postService.save(post, mockImage);

        //then
        Post resPost = postService.findById(post.getIdx());
        Assert.assertEquals(resPost.getIdx(), post.getIdx());
    }

    @Test
    @Transactional
    @WithMockUser(username = "108915067662391092609")
    public void 게시판_특정인물_조회() {
        // given
        Post post = Post.builder().content("특정인물 조회").build();
        postService.save(post,mockImage);

        List<Post> posts = postService.findByClientId();

        Assert.assertTrue(posts.stream().anyMatch((p) -> "특정인물 조회".equals(p.getContent())));

    }
    @Test
    public void 게시판_전체_조회() {
        //when
        Post post = new Post();
        post.setContent("test22");
        post.setUserIdx(userIdx);
        postService.save(post,mockImage);

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
    public void 게시글_1개_조회() {
        // given
        int postIdx = 12;

        Post newPost = postService.findById(postIdx);
        System.out.println(newPost.toString());
    }

    @Test
    public void 좋아요_누른_게시글이_맞는지_확인() {
        /// given
        int likeduserIdx = 35;
        Like like = Like.builder()
                .userIdx(likeduserIdx)
                .postIdx(originalPost.getIdx())
                .build();

        // when
        likeService.save(like);

        // then
        assertTrue(postService.findByIds(originalPost.getIdx(), likeduserIdx).isHasLiked());
    }

    @Test
    @WithMockUser(username = "108915067662391092609")
    @Transactional
    public void 게시글_지우기() {
        // given
        Post post = postMapper.findSortByIdAsc();

        // when
        postService.delete(post);

        // then
        // TODO 지웠을 때 image, tag, like, comment 모두 지워지는 지 확인 -> 다 입력하고 지워볼것
        assertNull(postMapper.findById(post.getIdx()));
        assertNull(imageMapper.findByPostIdx(post.getIdx()));
        assertNull(tagMapper.findTagsByPostId(post.getIdx()));
        assertNull(likeMapper.findLikes(post.getIdx()));
        assertNull(commentMapper.findCommentsByPostIdx(post.getIdx()));

        // TODO 사이즈 모달 안나옴 / size cover


    }
}



