package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import com.studygram.domain.Tag;
import com.studygram.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TagService tagService;

    public int save(Post post) {
        postMapper.save(post);
        List<String> tagList = tagService.saveTags(post);

        for(String tagContent : tagList) {
            String newContent = post.getContent().replace(tagContent, "<a link=''>"+tagContent+"</a>");
            post.setContent(newContent);

            postMapper.update(post);
        }

        return post.getIdx();
    }

    public Post findById(int postId) {
        return postMapper.findById(postId);
    }

    public List<Post> findAll(Integer limit, Integer offset) {
        return postMapper.findAll(limit, offset);
    }

    public Post update(Post post) {
        postMapper.update(post);
        return post;
    }

    public void delete(Post post) {
        postMapper.delete(post);
    }

    public List<Post> findByManyIds(List<PostTag> postTags) {

        List<Integer> postTagIds = new ArrayList<Integer>();
        for (PostTag postTag : postTags) {
            postTagIds.add(postTag.getPostIdx());
        }
        return postMapper.findByManyIds(postTagIds);

    }
}
