package com.studygram;

import com.studygram.domain.User;
import com.studygram.mapper.UserMapper;
import com.studygram.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
