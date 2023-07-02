package com.studygram.mapper;

import com.studygram.domain.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotificationMapper {
    int save(Notification notification);

    List<Notification> getNotifications(int toUserIdx);

    int getNotReadNotificationsCount(int userIdx);
}
