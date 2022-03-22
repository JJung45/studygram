package com.studygram;

import com.studygram.domain.User;
import com.studygram.mapper.UserMapper;
import com.studygram.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class OAuthLoginApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void 데이터베이스_연결_테스트() {
        userService.getAllUsers().stream()
                .map(User::getUserName)

                .forEach(System.out::println);
    }

}
