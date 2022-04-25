package com.studygram.service;

import com.studygram.domain.Comment;
import com.studygram.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentMapper commentMapper;

    @Test
    void 댓글작성() {
        // given
        Comment comm = Comment.builder()
                .postId(12)
                .userId(20)
                .content("test")
                .build();
        commentService.createComment(comm);
    }

    @Test
    void 댓글조회() {
        commentService.getCommentsListByPostID(12).stream()
                .map(Comment::getTags)
                .forEach(System.out::println);
    }


}
