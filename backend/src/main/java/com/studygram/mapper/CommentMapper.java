package com.studygram.mapper;

import com.studygram.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> findCommentsByPostIdx(int postIdx);
    List<Comment> findCommentsByPostIdxWithPaging(@Param("postIdx") int postIdx, @Param("limit") int limit, @Param("offset") long offset);
    Comment findByCommentIdx(int commentIdx);
    int getCommentCntByPostIdx(int postIdx);
    int save(Comment comment);
    int update(Comment comment);
    int deleteByCommentIdx(int commentIdx);
    int deleteByPostIdx(int postIdx);
    int findCommentCntByPostIdx(int postIdx);
    List<Comment> findCommentsByUserIdx(int userIdx);
}
