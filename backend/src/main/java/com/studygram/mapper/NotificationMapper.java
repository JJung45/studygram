package com.studygram.mapper;

import com.studygram.domain.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NotificationMapper {
    int save(Notification notification);
}
