package com.studygram.mapper;

import com.studygram.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LikeMapper {
    int save(Like like);
    List<Like> findLikesByPostId(int postId);
    List<Like> findLikesByCommentId(int commentId);
    int findLikesCntByPostId(int postId);
}
