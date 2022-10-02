package com.studygram.mapper;

import com.studygram.domain.Comment;
import com.studygram.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> findByPostId(int postId);
//    List<Tag> getTagListByPostId(int postId);
    Comment findByCommentId(int commentId);
    int save(Comment comment);
    int update(Comment comment);
    int deleteByCommentId(int commentId);
    int deleteByPostId(int postId);
}
