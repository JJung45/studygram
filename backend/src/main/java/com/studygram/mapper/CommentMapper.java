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
    int updateComment(Comment comment);
    int deleteCommentByCommentID(int commentId);
    int deleteCommentsByPostID(int postId);
}
