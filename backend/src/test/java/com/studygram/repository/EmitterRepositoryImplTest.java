package com.studygram.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class EmitterRepositoryImplTest {

    private EmitterRepository emitterRepository = new EmitterRepositoryImpl();

    @Test
    @DisplayName("Emitter 추가")
    public void save() throws Exception {
        // given
        int userIdx = 1;

        // when, then
        Assertions.assertDoesNotThrow(() -> emitterRepository.save(userIdx, new SseEmitter()));
    }

    @Test
    @DisplayName("Emitter 추가")
    public void saveTwoTimes() throws Exception {
        // given
        int userIdx = 1;
        // when, then
        Assertions.assertDoesNotThrow(() -> emitterRepository.save(userIdx, new SseEmitter()));
    }


    @Test
    @DisplayName("Emitter 제거")
    public void deleteByUserIdx() throws Exception {
        // given
        int userIdx = 1;

        // when
        emitterRepository.save(userIdx, new SseEmitter());
        emitterRepository.save(userIdx+1, new SseEmitter());

        Assertions.assertEquals(0, emitterRepository.findAllEmitterStartWithByUserIdx(userIdx).size());
    }
}
