package com.studygram.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EmitterRepositoryImpl implements EmitterRepository {

    private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(Integer userIdx, SseEmitter sseEmitter) {
        emitters.put(userIdx, sseEmitter);
        return sseEmitter;
    }

    @Override
    public Map<Integer, SseEmitter> findAllEmitterStartWithByUserIdx(Integer userIdx) {
        return emitters.entrySet().stream()
                .filter((emmiter) -> emmiter.getKey() == userIdx)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(Integer id) {
        emitters.remove(id);
    }

    //    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    //    @Override
//    public void saveEventCache(String eventCacheId, Object event) {
//        eventCache.put(eventCacheId, event);
//    }

//    @Override
//    public Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId) {
//        return eventCache.entrySet().stream()
//                .filter(entry -> entry.getKey().startsWith(memberId))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }

//    @Override
//    public void deleteAllEmitterStartWithId(String memberId) {
//        emitters.forEach(
//                (key, emitter) -> {
//                    if (key.startsWith(memberId)) {
//                        emitters.remove(key);
//                    }
//                }
//        );
//    }

//    @Override
//    public void deleteAllEventCacheStartWithId(String memberId) {
//        eventCache.forEach(
//                (key, emitter) -> {
//                    if (key.startsWith(memberId)) {
//                        eventCache.remove(key);
//                    }
//                }
//        );
//    }
}
