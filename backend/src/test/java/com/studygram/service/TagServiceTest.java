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

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TagServiceTest {

    private static final int userId = 5;
    private static final String test1Tag = "#rgrg";
    private static final String test2Tag = "#하이루";
    private static final String test3Tag = "#안hi";

    @Autowired
    PostService postService;
    Post originalPost;

    @Autowired
    TagService tagService;

    @Before
    public void beforeEach() {
        originalPost = new Post();
        originalPost.setContent("testassdfdsfsfsfs " + test1Tag + test2Tag + test3Tag);
        originalPost.setUserId(userId);
        postService.save(originalPost);
    }

    @Test
    public void 게시물이_저장된후_게시물에_샵이_붙은_단어가_태그로_저장된다() {
        //given
        Assert.assertNull(originalPost.getTags());

        //when
        tagService.saveTags(originalPost);

        //then
        Assert.assertNotNull(originalPost.getTags());

        ArrayList<String> tagContents = new ArrayList<>();
        for(Tag tag : originalPost.getTags()) {
            tagContents.add(tag.getContent());
        }
        Assert.assertTrue(tagContents.contains(test1Tag));
        Assert.assertTrue(tagContents.contains(test2Tag));
        Assert.assertTrue(tagContents.contains(test3Tag));
    }

    @Test
    public void 게시물이_업데이트된후_태그가_수정된다() {
        //given

        //when

        //then
    }

    @Test
    public void 검색시_해당_컨텐츠를_가진_태그의_연관된_게시물들을_모두_가져온다() {
        //given

        //when

        //then
    }

    @Test
    public void 검색시_유사한_컨텐츠를_가진_태그들을_모두_가져온다() {
        //given

        //when

        //then
    }
}
