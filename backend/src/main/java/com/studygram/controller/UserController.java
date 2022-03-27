package com.studygram.controller;

import com.studygram.common.oauth.OAuthApiResponse;
import com.studygram.domain.User;
import com.studygram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public OAuthApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      모르겠다... 모르겠어....
        User user = userService.getUser(principal.getUsername());

        return OAuthApiResponse.success("user", user);
    }

    @PostMapping("changeUserInfo")
    public OAuthApiResponse changeUserInfo() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        userService.updateUser(user);

        return OAuthApiResponse.success("user", user);
    }
}
