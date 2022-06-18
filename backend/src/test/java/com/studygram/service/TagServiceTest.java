package com.studygram.service;


import com.studygram.domain.Comment;
import com.studygram.domain.Like;
import com.studygram.domain.Post;
import com.studygram.domain.Tag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TagServiceTest {

    private static final int userId = 5;

    @Autowired
    PostService postService;
    Post originalPost;

    @Autowired
    TagService tagService;
    int tagCount;

    @Before
    public void beforeEach() {
        originalPost = new Post();
        originalPost.setContent("test");
        originalPost.setUserId(userId);
        postService.save(originalPost);

        tagCount = tagService.countAll();
    }

    @Test
    public void 태그_작성() {
        //given
        Tag tag = new Tag();
        tag.setContent("asdfasf");
        tag.setPostId(originalPost.getIdx());

        //when
        tagService.save(tag);

        //then
        int nowTagCount = tagService.countAll();
        Assert.assertEquals(++tagCount, nowTagCount);
    }

    @Test
    public void 태그_조회() {
        //given
        Tag tag = new Tag();
        tag.setContent("asdfasf");
        tag.setPostId(originalPost.getIdx());
        tagService.save(tag);

        Tag tag2 = new Tag();
        tag2.setContent("가나다라");
        tag2.setPostId(originalPost.getIdx());
        tagService.save(tag2);

        //when
        ArrayList<String> testContents = new ArrayList<>();
        testContents.add(tag.getContent());
        testContents.add(tag2.getContent());
        ArrayList<Tag> testTag = tagService.findByContents(testContents);

        //then
        //Assert.assertEquals(testTag.getIdx(),tag.getIdx());
        int a= 1;
    }

    @Test
    public void 태그_삭제() {
        //given
        Tag tag = new Tag();
        tag.setContent("asdfasf");
        tag.setPostId(originalPost.getIdx());
        tagService.save(tag);

        Tag tag2 = new Tag();
        tag2.setContent("가나다라");
        tag2.setPostId(originalPost.getIdx());
        tagService.save(tag2);

        Assert.assertThat(tag, hasProperty("idx"));
        Assert.assertThat(tag2, hasProperty("idx"));

        //when
        ArrayList<Integer> testTagIdxes = new ArrayList<>();
        testTagIdxes.add(tag.getIdx());
        testTagIdxes.add(tag2.getIdx());
        tagService.delete(testTagIdxes);

        //then
        Assert.assertNull(postService.findById(originalPost.getIdx()));
    }
}
