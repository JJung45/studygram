package com.studygram.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygram.controller.FollowController;
import com.studygram.domain.AuthReqModel;
import com.studygram.domain.Follow;
import com.studygram.domain.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class FollowServiceTest {
    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;

    @Mock
    private Follow follow;
    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() throws  Exception {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(FollowController.class).build();

        //given
        AuthReqModel authReqModel = new AuthReqModel("leehyeji", "1234");
        //when & then
        mockMvc.perform(post("/api/v1/auth/login")
                .content(objectMapper.writeValueAsString(authReqModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void 팔로잉() throws Exception {
        //given
        follow = Follow.builder()
//                .fromUserIdx(24)
                .toUserIdx(23)
                .build();

//        followService.follow(follow.getFromUserIdx(), follow.getToUserIdx()); // OK

        // perform
        mockMvc.perform(post("/follow")
                .content(objectMapper.writeValueAsString(follow))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void 언팔로잉() throws Exception {
        //given
        follow = Follow.builder()
                .fromUserIdx(24)
                .toUserIdx(25)
                .build();

        //perform
        mockMvc.perform(post("/unfollow")
                .content(objectMapper.writeValueAsString(follow))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void 사용자조회withFollowingInfo() throws Exception {
        //given
        int userIdx = 23;

        User user = userService.getUserInfo(userIdx);

        System.out.println("UserInfo: " + user.toString());

    }


}
