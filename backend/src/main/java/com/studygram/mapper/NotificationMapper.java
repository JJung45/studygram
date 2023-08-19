package com.studygram.mapper;

import com.studygram.domain.Notification;
import com.studygram.domain.NotificationComment;
import com.studygram.domain.NotificationLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface NotificationMapper {
    int save(Notification notification);

    int saveNotificationComment(NotificationComment notificationComment);

    int saveNotificationLike(NotificationLike notificationLike);

    List<Notification> getNotifications(int toUserIdx);

    int getNotReadNotificationsCount(int userIdx);
}
