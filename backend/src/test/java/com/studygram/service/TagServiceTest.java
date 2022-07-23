package com.studygram.service;


import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import com.studygram.domain.Tag;
import com.studygram.mapper.TagMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

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

    @Autowired
    TagMapper tagMapper;

    @Autowired
    PostTagService postTagService;

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
        Post newPost = postService.findById(originalPost.getIdx());
        List<PostTag> postTags = newPost.getTags();

        //then
        Assert.assertNotNull(postTags);
        ArrayList<String> tagContents = new ArrayList<>();
        for(PostTag postTag : postTags) {
            Tag tag = tagMapper.findTagById(postTag.getTagIdx());
            tagContents.add(tag.getContents());
        }
        Assert.assertTrue(tagContents.contains(test1Tag.replaceAll("#","")));
        Assert.assertTrue(tagContents.contains(test2Tag.replaceAll("#","")));
        Assert.assertTrue(tagContents.contains(test3Tag.replaceAll("#","")));
    }

    @Test
    public void 게시물이_업데이트된후_태그가_수정된다() {
        //given
        tagService.saveTags(originalPost);
        Post post = postService.findById(originalPost.getIdx());

        //when
        post.setContent("asdfsdf #update #hihi #post");
        tagService.updateTagsByPost(post);
        Post newPost = postService.findById(originalPost.getIdx());
        List<PostTag> postTags = newPost.getTags();

        //then
        ArrayList<String> tagContents = new ArrayList<>();
        for(PostTag postTag : postTags) {
            Tag tag = tagMapper.findTagById(postTag.getTagIdx());
            tagContents.add(tag.getContents());
        }

        Assert.assertFalse(tagContents.contains(test1Tag.replaceAll("#","")));
        Assert.assertFalse(tagContents.contains(test2Tag.replaceAll("#","")));
        Assert.assertFalse(tagContents.contains(test3Tag.replaceAll("#","")));

    }

    @Test
    public void 검색시_해당_컨텐츠를_가진_태그의_연관된_게시물들을_모두_가져온다() {
        //given
        tagService.saveTags(originalPost);
        String tagContent = test2Tag.replaceAll("#","");

        //when
        List<Post> list = tagService.findPostsByTag(tagContent);

        //then
        for (Post post : list) {
            assertThat(post.getContent(), containsString(tagContent));
        }
    }

    @Test
    public void 검색시_유사한_컨텐츠를_가진_태그들을_모두_가져온다() {
        //given
        tagService.saveTags(originalPost);

        //when
        String findSearch = "하";
        ArrayList<Tag> tags = tagService.findSimilarContent(findSearch);

        //then
        for (Tag tag : tags) {
            assertThat(tag.getContents(), containsString(findSearch));
        }
    }
}
