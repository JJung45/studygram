package com.studygram.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygram.controller.CommentController;
import com.studygram.domain.AuthReqModel;
import com.studygram.domain.Comment;
import com.studygram.mapper.CommentMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentMapper commentMapper;

    @Mock
    private Comment comment;
    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(CommentController.class).build();
    }


    @Test
    public void 댓글작성() throws Exception {
        // 왜 Test public 으로 해야해...?
        // 왜 인증해야해...?


        // given
        AuthReqModel authReqModel = new AuthReqModel("minchoi", "1234");
        // when & then
        mockMvc.perform(post("/api/v1/auth/login")
                .content(objectMapper.writeValueAsString(authReqModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        comment = Comment.builder()
                .postId(12)
                .userId(20)
                .content("test")
                .build();
//        commentService.createComment(comment);

        // when & then
        mockMvc.perform(post("/comment/save")
                .content(objectMapper.writeValueAsString(comment))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void 댓글조회() {
        commentService.getCommentsListByPostID(12).stream()
                .map(Comment::getTags)
                .forEach(System.out::println);
    }


}
