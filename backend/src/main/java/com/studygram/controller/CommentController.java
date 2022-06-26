package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.common.SimplePageRequest;
import com.studygram.domain.Comment;
import com.studygram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    // url="localhost:8090/comment?postId=1
    public List<Comment> getCommentsListByPostId(@RequestParam("postId") int postId, SimplePageRequest pageRequest) {
        // TODO 댓글 페이징 필요
        return commentService.getCommentsListByPostID(postId, pageRequest);
    }

    @GetMapping("/{commentId}")
//     url="localhost:8090/comment/1
    public Comment getCommentByCommentId(@PathVariable int commentId) {
        return commentService.getCommentByCommentID(commentId);
    }


    @PostMapping("/save") // value ={,} 다중 맵핑 가능
    public ApiResponse createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
        return ApiResponse.success(HttpStatus.OK.name(), null);
    }

    @PutMapping("/update")
    // url="localhost:8090/comment/update
    public void updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);

    }

    @DeleteMapping("/delete/{commentId}")
    // url="localhost:8090/comment/delete/{commentId}
    public void deleteCommentByCommentId(@PathVariable("commentId") int commentId) {
        commentService.deleteCommentByCommentId(commentId);
    }
}