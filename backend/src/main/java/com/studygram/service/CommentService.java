package com.studygram.service;

import com.studygram.common.ApiResponse;
import com.studygram.domain.Comment;
import com.studygram.domain.Tag;
import com.studygram.mapper.CommentMapper;
import com.studygram.utils.StringUtil;
import com.studygram.utils.TimeUtil;
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
        // 1. Post 데이터 있는지 확인
//        if(postService.getPost(comment.getPostId()).isNull()) {
//            ApiResponse.fail();
//        }

        // 2. 댓글 내용에서 Tag 추출하고 Insert
        String content = comment.getContent();
        List<String> tags = StringUtil.getTagsFromContent(content);
        for(String str : tags) {
            Tag tag = new Tag();
            tag.setCommentId(comment.getIdx());
            tag.setContent(str);
            /*
            if(tagService.save(tag) < 0) {
                ApiResponse.fail();
            }
             */

        }
        if(commentMapper.save(comment) < 0) {
            ApiResponse.fail();
        }

    }

    public void updateComment(Comment comment) {
        Comment originComment = commentMapper.findByCommentId(comment.getIdx());
        if (originComment == null) {
            log.error("Can't find Comment!");
            ApiResponse.notFoundFail(); // return 예외처리?
        } else {
            originComment.setContent(comment.getContent());
            originComment.setUpdatedDate(TimeUtil.getCurrentTime());
            System.out.println("originCom:"+originComment.toString());
            if (commentMapper.update(originComment) < 0) {
                System.out.println("Update 완료");
                ApiResponse.fail();
            }
        }

    }

    public void deleteCommentByCommentId(int commentId) {
        if(commentMapper.findByCommentId(commentId) == null) {
            log.error("Can't find Comment!");
            ApiResponse.notFoundFail();
        }

        if(commentMapper.deleteByCommentId(commentId) < 0) {
            ApiResponse.fail();
        }
    }

    public void deleteCommentsByPostId(int postId) {
//        if(postMapper.deleteByPostID(postId) == null) {
//            log.error("Not Found Post");
//            ApiResponse.notFoundFail();
//        }
        if(commentMapper.deleteByPostId(postId) < 0) {
            log.debug("Noting to Delete");
        }
    }

}
