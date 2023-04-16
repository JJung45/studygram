package com.studygram.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private User receiver; // 알림 받을 사람

    private Long id;

    private Boolean isRead; // 알림 읽음 여부

    private NotificationType notificationType; // 알람 타입
}
