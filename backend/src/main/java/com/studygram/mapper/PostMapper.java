package com.studygram.mapper;

import com.studygram.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    int save(Post post);
    Post findById(int id);
    List<Post> findAll();
}
