package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import com.studygram.domain.Tag;
import com.studygram.mapper.PostTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostTagService {

    @Autowired
    private PostTagMapper postTagMapper;

    public void deleteTagsByPost(Post post)
    {
        postTagMapper.deleteTagsByPost(post);
    }

    public void saveTagPost(Post post, Tag tags)
    {
        postTagMapper.save(post.getIdx(), tags.getIdx());
    }

    public List<PostTag> findPostsByTag(Tag tags)
    {
        return postTagMapper.findPostsByTag(tags);
    }

}
