package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.User;
import com.studygram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }

    @GetMapping("/name")
    public String getUserName() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        return user.getUserName();
    }

    @GetMapping("/info")
    public ApiResponse getUserInfo() {
        User user = userService.getUserInfo();
        return ApiResponse.success("user", user);
    }

    @GetMapping("/{userName}/info")
    public ApiResponse getUserInfoByUserName(@PathVariable(name = "userName") String userName) {
        User user = userService.getUserInfo(userName);
        return ApiResponse.success("user", user);
    }
}
