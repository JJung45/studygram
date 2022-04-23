package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
