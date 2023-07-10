package com.studygram.service;

import com.studygram.domain.NotificationType;
import com.studygram.domain.User;
import com.studygram.mapper.NotificationMapper;
import com.studygram.repository.EmitterRepository;
import com.studygram.repository.EmitterRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class NotificationServiceTest {

    EmitterRepository emitterRepository = new EmitterRepositoryImpl();

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("구독한다")
    public void subscribe() {
        NotificationService notificationService = new NotificationService(emitterRepository, notificationMapper, userService);

        // given
        int userIdx = 1;

        // when
        SseEmitter emitter = notificationService.subscribe(userIdx);

        // then
        Assertions.assertEquals(1, emitterRepository.findAllEmitterStartWithByUserIdx(userIdx).size());
    }

    @Test
    @DisplayName("좋아요 알림을 보낸다")
    public void send() {
        NotificationService notificationService = new NotificationService(emitterRepository, notificationMapper, userService);

        // given
        User toUser = User.builder().idx(1).build();
        User fromUser = User.builder().idx(2).build();
        notificationService.subscribe(toUser.getIdx());
        notificationService.subscribe(fromUser.getIdx());

        // when
        notificationService.send(toUser.getIdx(), fromUser.getIdx(), NotificationType.LIKE);

        // then
        Map<Integer, SseEmitter> emmiter = emitterRepository.findAllEmitterStartWithByUserIdx(toUser.getIdx());
    }
}
