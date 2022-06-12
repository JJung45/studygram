package com.studygram.mapper;

import com.studygram.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    int save(Tag tag);
    List<Tag> findTagsByPostId(int postId);
    List<Tag> findTagsByCommentId(int commentId);

}
