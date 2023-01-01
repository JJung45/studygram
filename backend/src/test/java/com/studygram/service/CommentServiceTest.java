package com.studygram.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygram.common.SimplePageRequest;
import com.studygram.controller.CommentController;
import com.studygram.domain.AuthReqModel;
import com.studygram.domain.Comment;
import com.studygram.mapper.CommentMapper;
import com.studygram.utils.StringUtil;
import org.junit.Assert;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@Transactional
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
    public void setup() throws Exception {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(CommentController.class).build();

        // given
        AuthReqModel authReqModel = new AuthReqModel("leehyeji", "1234");
        // when & then
        mockMvc.perform(post("/api/v1/auth/login")
                .content(objectMapper.writeValueAsString(authReqModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    public void 댓글작성() throws Exception {
        // given
        int i = 1;
        while(i < 98){
            Comment comment = Comment.builder()
                    .postIdx(48)
                    .userIdx(27)
                    .content("testComment" + i)
                    .build();

            commentService.createComment(comment);
            i++;
        };
//        comment = Comment.builder()
//                .postId(28)
//                .userId(24)
//                .content("testComment")
//                .build();

        // when & then
//        mockMvc.perform(post("/comment/save")
//                .content(objectMapper.writeValueAsString(comment))
//                .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());
    }

    @Test
    public void 댓글조회() throws Exception{
        // given
        int postId = 28;

        // when & then
//       mockMvc.perform(get("/comment/" + postId))
       mockMvc.perform(get("/comment?postId=" + postId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void 댓글조회withPaging() throws Exception {
        // given
        int postId = 28;
        int limit = 7;
        int offset = 0;

        List<Comment> commentList = commentService.getCommentsListByPostID(postId);
        Assert.assertEquals(commentList.size(), 7);

        // when & then
//       mockMvc.perform(get("/comment/" + postId))
//        mockMvc.perform(get("/comment?postId=" + postId + "&limit=" + limit))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
    }

    @Test
    public void 댓글수정() throws Exception {
        // given
        comment = Comment.builder()
                .idx(2)
                .postIdx(12)
                .userIdx(20)
                .content("updateTEST")
                .build();
        // when & then
        mockMvc.perform(put("/comment/update")
                .content(objectMapper.writeValueAsString(comment))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void 댓글삭제() throws Exception {
        // given
        int commentId = 4;

        // when & then
        mockMvc.perform(delete("/comment/delete/"+commentId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void 해시태그추출(){
        String cont = "안녕하세요? 이혜지#토리#비숑#토리 테스트";
        List<String> tags = StringUtil.getTagsFromContent(cont);
        System.out.println(tags.toString());
    }

}
