package com.studygram.controller;

import com.studygram.common.oauth.ApiResponse;
import com.studygram.domain.Comment;
import com.studygram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment")
    // url="localhost:8090/comment?postId=
    public List<Comment> getCommentsList(@RequestParam("postId") int postId) {
        return commentService.getCommentsListByPostID(postId);
    }

    @PostMapping("/comment/save") // value ={,} 다중 맵핑 가능
    public ApiResponse createComment(@RequestBody Comment comment) {
//        if(postService.getPost(comment.getPostId()) == null)
//            return ApiResponse.fail();
        commentService.createComment(comment);
        return ApiResponse.success(HttpStatus.OK.name(), null);
    }

    @PutMapping("/comment/upd")
    public void modifyComment(Comment comment) {
        commentService.updateComment(comment);

    }

    @DeleteMapping("/comment/del")
    // url="localhost:8090/comment/del?commentId=
    public void removeCommentByCommentId(@RequestParam("commentId") int commentId) {
        commentService.deleteCommentByCommentId(commentId);
    }

    // 나중에 Post 지울때 Comment 지우는거 같이 할 필요 있음
}