package com.studygram.mapper;

import com.studygram.domain.Post;
import com.studygram.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Mapper
@Repository
public interface PostTagMapper {
    void deleteTagsByPost(Post post);
    void save(@Param("post") Post post, @Param("tag") Tag tag);
    ArrayList<Post> findPostsByTag(Tag tag);
}
