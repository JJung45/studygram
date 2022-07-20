package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import com.studygram.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public int save(Post post) {
        return postMapper.save(post);
    }

    public Post findById(int postId) {
        return postMapper.findById(postId);
    }

    public List<Post> findAll() {
        return postMapper.findAll();
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
