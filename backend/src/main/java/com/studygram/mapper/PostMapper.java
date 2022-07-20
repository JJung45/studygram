package com.studygram.mapper;

import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    int save(Post post);
    Post findById(int id);
    List<Post> findAll();
    List<PostTag> findPostTags(int postIdx);
    void update(Post post);
    void delete(Post post);
    List<Post> findByManyIds(@Param("postTagIds") List<Integer> postTagIds);
}
