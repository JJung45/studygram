package com.studygram.mapper;

import com.studygram.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectCommentsListByPostID(int postId);
    int insertComment(Comment comment);
    int updateComment(Comment comment);
    int deleteCommentByCommentID(int commentId);
    int deleteCommentsByPostID(int postId);
}
