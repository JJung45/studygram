package com.studygram.service;

import com.studygram.domain.NotificationType;
import com.studygram.domain.User;
import com.studygram.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class NotificationService {

    private final EmitterRepository emitterRepository;

//    private final UserService userService;

    public SseEmitter subscribe(int userIdx) {
        SseEmitter emitter = emitterRepository.save(userIdx, new SseEmitter());

        // 시간이 만료된 경우 자동으로 레포지토리에서 삭제 가능하도록 하는 콜백
        emitter.onCompletion(() -> emitterRepository.deleteById(userIdx));
        emitter.onTimeout(() -> emitterRepository.deleteById(userIdx));

        // 처음부터 데이터 안보내면 503 에러 발생함으로 더미 데이터를 보낸다.
        sendNotification(emitter, userIdx, "dummy data " + System.currentTimeMillis());

        return emitter;
    }

    private void sendNotification(SseEmitter emitter, Integer emmiterId, String notification) {
        try {
            emitter.send(notification);
        } catch (IOException e) {
            emitterRepository.deleteById(emmiterId);
        }
    }

    public void send(User receiver, NotificationType notificationType) {
        // TODO 알람 디비 저장
        Map<Integer, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByUserIdx(receiver.getIdx());
        emitters.forEach((emmiterId, emmiter) -> sendNotification(emmiter, emmiterId, notificationType.getMessage()));
    }
}
