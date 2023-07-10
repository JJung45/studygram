package com.studygram.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
    SseEmitter save(Integer userIdx, SseEmitter sseEmitter); // Emmiter 저장
    Map<Integer, SseEmitter> findAllEmitterStartWithByUserIdx(Integer userIdx);
    void deleteById(Integer id);

//    void saveEventCache(String emitterId, Object event); // 이벤트 저장

//    Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId);

//    void deleteAllEmitterStartWithId(String userIdx);

//    void deleteAllEventCacheStartWithId(String memberId);
}
