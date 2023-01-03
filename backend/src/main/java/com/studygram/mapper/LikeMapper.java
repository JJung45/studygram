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
    Like findByPostUser(@Param("postIdx")int postIdx, @Param("userIdx")int userIdx);
    boolean hasLikedPost(@Param("postIdx") int postIdx, @Param("userIdx") int userIdx);
    int countAll();
    Like randOneIdx();
    List<User> findLikersByPostIdx(int postIdx);
    List<Like> findLikesByCommentIdx(int commentIdx);
    int findLikesCntByPostIdx(int postIdx);
}
