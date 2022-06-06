package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.Tag;
import com.studygram.mapper.PostMapper;
import com.studygram.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public int save(Tag tag) {
        return tagMapper.save(tag);
    }
}
