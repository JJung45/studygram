package com.studygram.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Integer idx;

    private User fromUser;

    private int fromUserIdx; // 알림 보낸 사람

    private User toUser;

    private int toUserIdx; // 알림 받을 사람

    private Boolean isRead; // 알림 읽음 여부

    private Integer notificationIdx;

    private Post post;

    private Date createdDate;

    private String notificationType; // 알람 타입

    private String message;
}
