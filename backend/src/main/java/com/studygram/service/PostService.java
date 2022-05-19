package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // TODO 댓글, 좋아요, 태그 연결해서 업데이트
        postMapper.update(post);
        return post;
    }

    public void delete(Post post) {
        postMapper.delete(post);
    }
}