package com.studygram.service;

import com.studygram.common.oauth.ApiResponse;
import com.studygram.common.oauth.ApiResponseHeader;
import com.studygram.domain.Comment;
import com.studygram.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
            ApiResponse.fail();
        }
    }

    public void updateComment(Comment comment) {
        if(commentMapper.findByCommentId(comment.getIdx()) == null) {
            log.error("Can't find Comment!");
            ApiResponse.notFoundFail();
        }

        if(commentMapper.update(comment) < 0) {
            ApiResponse.fail();
        }
    }

    public void deleteCommentByCommentId(int commentId) {
        if(commentMapper.findByCommentId(commentId) == null) {
            log.error("Can't find Comment!");
            ApiResponse.notFoundFail();
        }

        if(commentMapper.deleteByCommentID(commentId) < 0) {
            ApiResponse.fail();
        }
    }

    public void deleteCommentsByPostId(int postId) {
//        if(postMapper.deleteByPostID(postId) == null) {
//            log.error("Not Found Post");
//            ApiResponse.notFoundFail();
//        }
        if(commentMapper.deleteByPostID(postId) < 0) {
            log.debug("Noting to Delete");
        }
    }

}
