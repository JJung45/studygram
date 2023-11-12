package com.studygram.controller;

import com.studygram.common.ApiResponse;
import com.studygram.domain.User;
import com.studygram.service.ImageUploadService;
import com.studygram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
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

    @PutMapping("/{userIdx}/profileImage")
    public ApiResponse updateProfileImage(@PathVariable int userIdx, @RequestParam(value="fileImage") MultipartFile file) throws Exception
    {
//        try{
            System.out.println("File Info = "+file);
            String profileImgUrl = userService.updateProfileImage(userIdx, file);
            return ApiResponse.success("profileImageUrl", profileImgUrl);
//        } catch(MaxUploadSizeExceededException e) {
//            return ApiResponse.fail("Max File Size Exception");
//        } catch (Exception e) {
//            return ApiResponse.fail("Fail to Update ProfileImage");
//        }
    }

    @GetMapping("/{userIdx}/activities")
    public ApiResponse getMyActivities(@PathVariable int userIdx) {

        // 좋아요(게시물), 댓글 목록
        return ApiResponse.success("getMyActivities", null);
    }

    @PutMapping("/update")
    public ApiResponse updateUserInfo(@RequestBody User user, @RequestParam(value="fileImage") @Nullable MultipartFile file) {
        // 프로필 수정 (프로필사진, 프로필 메시지, 공개/비공개)
        System.out.println("userInfo="+user.getIdx());
        try {
            userService.updateUserInfo(user, file);
            return ApiResponse.success("user", user);
        } catch (Exception e) {
            log.error("Not Found user!");
            return ApiResponse.notFoundFail();
        }
    }
}
