package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.Tag;
import com.studygram.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagService postTagService;

    public void saveTags(Post post) {
        String content = post.getContent();
        String[] tagList = content.substring(content.lastIndexOf("#")+1).split("#");

        // 게시물이 저장된 다음에 가져와야할듯!
        int postIdx = post.getIdx();
        for(String tagContent : tagList) {
            String newTagContent = tagContent.replaceFirst("#","");

            Tag tag = new Tag();
            tag.setPostId(postIdx);
            tag.setContent(newTagContent);
            postTagService.saveTagPost(post, tag);
        }

    }

    public void updateTagsByPost(Post post) {
        postTagService.deleteTagsByPost(post);
        saveTags(post);
    }

    //검색(태그클릭)
    public ArrayList<Post> findPostsByTag(String search) {
        Tag tag = tagMapper.findContent(search);
        return postTagService.findPostsByTag(tag);
    }

    //자동완성
    public ArrayList<Tag> findTags(String search) {
        return tagMapper.findSimilarContent(search);
    }
}
