package com.studygram.mapper;

import com.studygram.domain.Like;
import com.studygram.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface LikeMapper {
    int save(Like like);
    void delete(Like like);
    Like findByPostUser(@Param("postId")int postId, @Param("userId")int userId);
    boolean hasLikedPost(@Param("postId") int postId, @Param("userId") int userId);
    int countAll();
    Like randOneIdx();
    List<User> findLikesByPostId(int postId);
    List<Like> findLikesByCommentIdx(int commentIdx);
    int findLikesCntByPostId(int postId);
}
