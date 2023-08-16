package com.studygram.service;

import com.studygram.AwsS3TestConfig;
import com.studygram.config.AwsS3Config;
import io.findify.s3mock.S3Mock;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

//@Import(AwsS3TestConfig.class)
@SpringBootTest
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserServiceTest {

//    @Autowired
//    private S3Mock s3Mock;

    @Autowired
    private AmazonS3ResourceStorage amazonS3ResourceStorage;

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

    @Test
    @DisplayName("user profile update")
    public void updateProfileImage() {



    }

}
