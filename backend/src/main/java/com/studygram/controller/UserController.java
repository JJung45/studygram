package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.User;
import com.studygram.service.ImageUploadService;
import com.studygram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/{userIdx}/profile")
    public ApiResponse updateProfileImage(@PathVariable int userIdx, @RequestParam(value="fileImage") MultipartFile file)
    {
        try{
            System.out.println("File Info = "+file);
            String profileImgUrl = userService.updateProfileImage(userIdx, file);
            return ApiResponse.success("profileImageUrl", profileImgUrl);
        } catch(MaxUploadSizeExceededException e) {
            return ApiResponse.fail("Max File Size Exception");
        } catch (Exception e) {
            return ApiResponse.fail("Fail to Update ProfileImage");
        }
    }
}
