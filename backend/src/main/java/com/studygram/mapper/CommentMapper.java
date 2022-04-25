package com.studygram.mapper;

import com.studygram.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> findByPostId(int postId);
    int save(Comment comment);
    int update(Comment comment);
    int deleteByCommentID(int commentId);
    int deleteByPostID(int postId);
    Comment findByCommentId(int commentId);
}
