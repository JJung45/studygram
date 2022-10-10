package com.studygram;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygram.common.oauth.ProviderType;
import com.studygram.common.oauth.RoleType;
import com.studygram.controller.AuthController;
import com.studygram.domain.AuthReqModel;
import com.studygram.domain.User;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
public class AuthControllerTest {
    @Mock
    private User user;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(AuthController.class).build();
    }

    @Test
    @DisplayName("로컬 회원 가입 테스트")
    public void saveTest() throws Exception
    {
        // given
        user = User.builder()
                .userName("minchoi")
                .passwd("1234")
                .providerType(ProviderType.LOCAL)
                .roleType(RoleType.USER)
                .build();

        // when & then
        mockMvc.perform(post("/api/v1/auth/save")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 과정 테스트")
    public void totalLoginTest() throws Exception {
        // given
        AuthReqModel authReqModel = new AuthReqModel("minchoi", "1234");
        // when & then
        mockMvc.perform(post("/api/v1/auth/login")
                        .content(objectMapper.writeValueAsString(authReqModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
