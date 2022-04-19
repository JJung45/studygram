package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.User;
import com.studygram.mapper.PostMapper;
import com.studygram.mapper.UserMapper;
import com.studygram.mapper.UserRefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public void save(Post post) {
        postMapper.save(post);
    }
}
