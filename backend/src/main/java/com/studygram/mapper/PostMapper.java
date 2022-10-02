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
    Post findByIds(@Param("postId") Integer postId, @Param("userId") Integer userId);
    List<Post> findAll(@Param("limit") @Nullable Integer limit, @Param("offset") @Nullable Integer offset);
    List<PostTag> findPostTags(int postIdx);
    void update(Post post);
    void delete(Post post);
}
