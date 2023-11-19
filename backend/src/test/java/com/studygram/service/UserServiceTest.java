package com.studygram.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygram.AwsS3TestConfig;
import com.studygram.config.AwsS3Config;
import com.studygram.controller.UserController;
import com.studygram.domain.AuthReqModel;
import com.studygram.domain.User;
import io.findify.s3mock.S3Mock;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Import(AwsS3TestConfig.class)
//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserServiceTest {

//    @Autowired
//    private S3Mock s3Mock;

    @Autowired
    UserService userService;

    @Mock
    private User mockUser;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AmazonS3ResourceStorage amazonS3ResourceStorage;

    AutoCloseable openMocks;
    ObjectMapper objectMapper = new ObjectMapper();

//    @AfterEach
//    void tearDown() {
//        s3Mock.stop();
//    }

    @Test
    @DisplayName("AWS S3 이미지 업로드 테스트")
    public void upload() throws IOException {
        //given
        String path = "test.png";
        String contentType = "image/png";

        MockMultipartFile mockMultipartFile = new MockMultipartFile("test", path, contentType, "test".getBytes());

        //when
        String imgUrl = amazonS3ResourceStorage.store(mockMultipartFile.getOriginalFilename(), mockMultipartFile);
        String replacedImgUrl = imgUrl.replace("https", "http");

        //then
        System.out.println("imageUrl = " + imgUrl);
        System.out.println("replacedImgUrl = " + replacedImgUrl);

        // Amazon Url : com.studygrm.instagram.bucket.s3.ap-northeast-2.amazonaws.com
        String S3URL = "com.studygrm.instagram.bucket.s3.ap-northeast-2.amazonaws.com";
        assertThat(imgUrl).isEqualTo("https://"+S3URL+"/"+path);
        assertThat(replacedImgUrl).isEqualTo("http://"+S3URL+"/"+path);
    }

    @BeforeEach
    public void setup() throws Exception {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(UserController.class).build();

        //given
        AuthReqModel authReqModel = new AuthReqModel("leehyeji", "1234");
        //when & then
        mockMvc.perform(post("/api/v1/auth/login")
                        .content(objectMapper.writeValueAsString(authReqModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("user profile update")
    public void updateProfileInfoTest() {
        User user = userService.getUserInfo(1);
        System.out.println("기존 User Info ="+ user);
        user.setProfileMsg("새로운 프로필 메시지");
        user.setPublicType(false);

        try {
            userService.updateUserInfo(user, null);
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("변경된 UserInfo = " + user);

    }

    @Test
    @DisplayName("Get My Activities")
    public void getMyActivities(){
        try {
            Map<String, Object> ResMap = userService.getMyActivities(1);
            System.out.println(ResMap.get("likedPostList"));
            System.out.println(ResMap.get("commentedPostList"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
