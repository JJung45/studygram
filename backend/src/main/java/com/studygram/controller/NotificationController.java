package com.studygram.controller;

import com.studygram.domain.User;
import com.studygram.service.NotificationService;
import com.studygram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    
    @GetMapping(value = "/subscribe/{userIdx}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable int userIdx) {

        return notificationService.subscribe(userIdx);
    }
}
