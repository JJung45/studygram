package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationComment {
    int idx;
    int notificationIdx;
    int commentIdx;
}
