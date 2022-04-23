package com.studygram.service;

import com.studygram.domain.Comment;
import com.studygram.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<Comment> getCommentsListByPostID(int postId) {
        return commentMapper.findByPostId(postId);
    }

    public void createComment(Comment comment) {
        if(commentMapper.save(comment) < 0) {
            // 실패 예외처리
        }
    }

    public void updateComment(Comment comment) {
        if(commentMapper.updateComment(comment) < 0) {
            // 실패 예외처리
        }
    }

    public void deleteCommentByCommentId(int commentId) {
        if(commentMapper.deleteCommentByCommentID(commentId) < 0) {
            // 실패 예외처리
        }
    }

    public void deleteCommentsByPostId(int postId) {
        if(commentMapper.deleteCommentsByPostID(postId) < 0) {
            //실패예외처리

        }
    }

}
