package com.studygram.service;

import com.studygram.domain.Like;
import com.studygram.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeMapper likeMapper;

    public int save(Like like) {
        return likeMapper.save(like);
    }

    public int countAll() {
        return likeMapper.countAll();
    }

    public void delete(Like like) {
        likeMapper.delete(like);
    }

    public Like randOneIdx() {
        return likeMapper.randOneIdx();
    }
}
