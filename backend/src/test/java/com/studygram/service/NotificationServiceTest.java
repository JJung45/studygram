package com.studygram.service;

import com.studygram.domain.NotificationType;
import com.studygram.domain.User;
import com.studygram.repository.EmitterRepository;
import com.studygram.repository.EmitterRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public class NotificationServiceTest {

    EmitterRepository emitterRepository = new EmitterRepositoryImpl();

    NotificationService notificationService = new NotificationService(emitterRepository);

    @Test
    @DisplayName("구독한다")
    public void subscribe() {
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
        // given
        User receivedUser = User.builder().idx(1).build();
        User sendUser = User.builder().idx(2).build();
        notificationService.subscribe(receivedUser.getIdx());
        notificationService.subscribe(sendUser.getIdx());

        // when
        notificationService.send(receivedUser, NotificationType.LIKE);

        // then
        // 알림 받는 테스트를 어떻게 할지?
        Map<Integer, SseEmitter> emmiter = emitterRepository.findAllEmitterStartWithByUserIdx(receivedUser.getIdx());
    }
}
