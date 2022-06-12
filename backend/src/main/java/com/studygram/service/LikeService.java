package com.studygram.service;

import com.studygram.domain.Like;
import com.studygram.domain.Tag;
import com.studygram.mapper.LikeMapper;
import com.studygram.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeMapper likeMapper;

    public int save(Like like) {
        return likeMapper.save(like);
    }
}
