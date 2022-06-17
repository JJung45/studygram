package com.studygram.mapper;

import com.studygram.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    int save(Post post);
    Post findById(int id);
    List<Post> findAll(@Param("limit") @Nullable Integer limit, @Param("offset") @Nullable Integer offset);
    void update(Post post);
    void delete(Post post);
}
