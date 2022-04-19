package com.studygram.controller;

import com.studygram.domain.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class CommentController {
    @GetMapping("/comments")
    public List<Comment> getCommentsList() {
        return null;
    }

    @PostMapping("/comments")
    public void createCommnets() {

    }

    @PutMapping("/comments")
    public void modifyComments() {

    }

    @DeleteMapping("/comments")
    public void removeComments() {

    }

}
