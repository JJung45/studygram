package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationLike {
    int idx;
    int notificationIdx;
    int likeIdx;

}
