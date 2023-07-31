package com.studygram.service;

import com.studygram.common.oauth.AuthTokenProvider;
import com.studygram.config.AppProperties;
import com.studygram.domain.FileDetail;
import com.studygram.domain.Image;
import com.studygram.domain.User;
import com.studygram.mapper.FollowMapper;
import com.studygram.mapper.ImageMapper;
import com.studygram.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import static com.studygram.service.ImageUploadService.MAX_FILE_SIZE;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final AppProperties appProperties;
    @Autowired
    private final AuthTokenProvider tokenProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final FollowMapper followMapper;
    @Autowired
    private final AmazonS3ResourceStorage amazonS3ResourceStorage;
    @Autowired
    private final ImageMapper imageMapper;

    public User getUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return getUser(username);
    }

    public User getUser(String clientId) {
        return userMapper.findByClientId(clientId);
    }

    public User getUserInfo() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return userMapper.findInfoByClientId(username);
    }

    public User getUserInfo(String userName) {
        return userMapper.findInfoByUserName(userName);
    }

    public User getUserInfo(int userIdx) {
        User userInfo = userMapper.findByUserIdx(userIdx);
        userInfo.setFollowingCnt(followMapper.countFollowings(userIdx));
        userInfo.setFollowing(followMapper.getFollowings(userIdx));
        userInfo.setFollowersCnt(followMapper.countFollowers(userIdx));
        userInfo.setFollowers(followMapper.getFollowers(userIdx));
        return userInfo;
    }

    public User getClientId(String clientId) {
        return userMapper.findByClientId(clientId);
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public void save(User user) {
        user.setPasswd(passwordEncoder.encode(user.getPasswd()));
        userMapper.save(user);
    }

    public String updateProfileImage(int userIdx, MultipartFile file) throws Exception {
        User user = userMapper.findByUserIdx(userIdx);
        if(user == null) {
            throw new Exception("Not Found User");
        }


        // AWS S3 저장
        Long fileSize = file.getSize();
        if(fileSize > MAX_FILE_SIZE) {
            throw new MaxUploadSizeExceededException(fileSize);
        }

        FileDetail fileDetail = FileDetail.multipartOf(file);
        String imgUrl = amazonS3ResourceStorage.store(fileDetail.getPath(), file).replace("https", "http");

        Image image = new Image();
        image.setOriginalFilename(fileDetail.getPath());
        image.setStorePath(imgUrl);
        image.setCreatedDate(new Date());

        int imageIdx = imageMapper.save(image);
        image.setIdx(imageIdx);
        imageMapper.saveUserImage(imageIdx, userIdx);

        // user update
//        if(userMapper.updateProfileImage(userIdx, imgUrl) > 0 ) {
//            return imgUrl;
//        } else {
//            return null;
//        }
        return imgUrl;
    }

}
