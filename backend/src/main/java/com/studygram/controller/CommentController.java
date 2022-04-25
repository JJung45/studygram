package com.studygram.controller;

import com.studygram.domain.Comment;
import com.studygram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Comment> getCommentsList(@RequestParam("postId") int postId) {
        return commentService.getCommentsListByPostID(postId);
    }

    @PostMapping(value ="/comment/new") // value ={,} 다중 맵핑 가능
    public void createComment(Comment comment) {
        commentService.createComment(comment);

    }

    @PutMapping("/comment/upd")
    public void modifyComment(Comment comment) {
        commentService.updateComment(comment);

    }

    @DeleteMapping("/comment/del")
    public void removeCommentByCommentId(@RequestParam("commentId") int commentId) {
        commentService.deleteCommentByCommentId(commentId);
    }

//    @DeleteMapping("/comment/del2")
//    public void removeCommentByPostId(@RequestParam("postId") int postId) {
//        commentService.deleteCommentByCommentId(postId);
//    }
}
