package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.Tag;
import com.studygram.mapper.PostTagMapper;
import com.studygram.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PostTagService {

    @Autowired
    private PostTagMapper postTagMapper;

    public void deleteTagsByPost(Post post)
    {
        postTagMapper.deleteTagsByPost(post);
    }

    public void saveTagPost(Post post, Tag tag)
    {
        postTagMapper.save(post.getIdx(), tag.getIdx());
    }

    public ArrayList<Post> findPostsByTag(Tag tag)
    {
        return postTagMapper.findPostsByTag(tag);
    }

}
