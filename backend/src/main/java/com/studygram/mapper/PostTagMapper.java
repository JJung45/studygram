package com.studygram.mapper;

import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import com.studygram.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface PostTagMapper {
    void deleteTagsByPost(Post post);
    void save(@Param("post") Integer post, @Param("tag") Integer tag);
    List<PostTag> findTags(Post post);
    List<PostTag> findPostsByTag(Tag tags);
}
