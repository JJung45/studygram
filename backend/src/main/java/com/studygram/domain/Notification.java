package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Long idx;

    private int fromUserIdx; // 알림 보낸 사람

    private int toUserIdx; // 알림 받을 사람

    private Boolean isRead; // 알림 읽음 여부

    private String notificationType; // 알람 타입
}
