package com.studygram.mapper;

import com.studygram.domain.Post;
import com.studygram.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    int save(Tag tag);
    List<Tag> findTagsByPostId(int postIdx);
    List<Tag> findTagsByCommentId(int commentIdx);
    void deleteTagsByPost(Post post);
    Tag findContent(String search);
    ArrayList<Tag> findSimilarContent(String search);
    Tag findTagById(Integer tagIdx);
}
