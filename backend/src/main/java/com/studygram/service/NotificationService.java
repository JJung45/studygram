package com.studygram.service;

import com.studygram.domain.Like;
import com.studygram.domain.Notification;
import com.studygram.domain.NotificationType;
import com.studygram.domain.User;
import com.studygram.mapper.NotificationMapper;
import com.studygram.repository.EmitterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final EmitterRepository emitterRepository;

    private final NotificationMapper notificationMapper;

    private final UserService userService;

    public NotificationService(EmitterRepository emitterRepository, NotificationMapper notificationMapper, UserService userService) {
        this.emitterRepository = emitterRepository;
        this.notificationMapper = notificationMapper;
        this.userService = userService;
    }

    public SseEmitter subscribe(int userIdx) {
        SseEmitter emitter = emitterRepository.save(userIdx, new SseEmitter(60 * 1000L));

        // 시간이 만료된 경우 자동으로 레포지토리에서 삭제 가능하도록 하는 콜백
        emitter.onCompletion(() -> emitterRepository.deleteById(userIdx));
        emitter.onTimeout(() -> emitterRepository.deleteById(userIdx));

        // 처음부터 데이터 안보내면 503 에러 발생함으로 더미 데이터를 보낸다.
        sendNotification(emitter, userIdx, "dummy");

        return emitter;
    }

    private void sendNotification(SseEmitter emitter, Integer emmiterId, String notification) {
        try {
            emitter.send(notification);
        } catch (IOException e) {
            emitterRepository.deleteById(emmiterId);
        }
    }

    public void send(int toUserIdx, int fromUserIdx, Like like, NotificationType notificationType) {
        // TODO 민경쓰 확인
        Notification notification = Notification
                .builder()
                .toUserIdx(toUserIdx)
                .fromUserIdx(fromUserIdx)
                .isRead(false)
                .likeIdx(like != null ? like.getIdx() : null)
                .notificationType(notificationType.getType())
                .build();

        notificationMapper.save(notification);
        Map<Integer, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByUserIdx(toUserIdx);
        emitters.forEach((emmiterId, emmiter) ->
                sendNotification(emmiter, emmiterId, getMessage(notification))
        );
    }

    public void send(int toUserIdx, int fromUserIdx, NotificationType notificationType) {
        send(toUserIdx, fromUserIdx, null, notificationType);
    }

    private String getMessage(Notification notification) {
        User fromUserInfo = userService.getUserInfo(notification.getFromUserIdx());
        return fromUserInfo.getUserName() + "님이 " + getNotificationTypeMessage(notification.getNotificationType());
    }

    private String getNotificationTypeMessage(String type) {
        if ("좋아요".equals(type)) {
            return NotificationType.LIKE.getMessage();
        } else if ("팔로우".equals(type)) {
            return NotificationType.FOLLOW.getMessage();
        } else if ("덧글".equals(type)) {
            return NotificationType.COMMENT.getMessage();
        } else {
            return NotificationType.DUMMY.getMessage();
        }
    }

    public List<Notification> getNotifications() {
        User toUser = userService.getUser();

        List<Notification> notifications = notificationMapper.getNotifications(toUser.getIdx());

        for (Notification notification : notifications) {
            notification.setMessage(getMessage(notification));
        }

        return notifications;
    }

    public List<Notification> getNotifications(int userIdx) {
        return notificationMapper.getNotifications(userIdx);
    }

    public int getNotReadNotificationsCount() {
        User user = userService.getUser();
        return notificationMapper.getNotReadNotificationsCount(user.getIdx());
    }

    public int getNotReadNotificationsCount(int userIdx) {
        return notificationMapper.getNotReadNotificationsCount(userIdx);
    }
}
