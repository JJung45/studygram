package com.studygram.service;

import com.studygram.common.oauth.AuthTokenProvider;
import com.studygram.config.AppProperties;
import com.studygram.domain.FileDetail;
import com.studygram.domain.Image;
import com.studygram.domain.Post;
import com.studygram.domain.User;
import com.studygram.mapper.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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

    @Autowired
    private final PostMapper postMapper;

    @Autowired
    private final CommentMapper commentMapper;

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

        imageMapper.save(image);
        imageMapper.saveUserImageRel(image.getIdx(), userIdx);

        return imgUrl;
    }

    public Map<String, Object> getMyActivities(int userIdx) throws NotFoundException {
        Map<String, Object> myActivityMap = new HashMap<>();
        // 좋아요
        List<Integer> likedPostIdxList = postMapper.findPostIdxByLikeUserIdx(userIdx);
        List<Post> likedPostList = new ArrayList<>();
        if(!likedPostIdxList.isEmpty()) {
            for(int idx : likedPostIdxList) {
                Post selectedPost = postMapper.findById(idx);
                if(selectedPost == null) {
                    throw new NotFoundException("There's no Post idx"+idx);
                }
                likedPostList.add(selectedPost);
            }
            myActivityMap.put("likedPostList", likedPostList);
        }

        // 댓글
        List<Integer> commentedPostIdxList = commentMapper.findPostIdxByCommentUserIdx(userIdx);
        List<Post> commentedPostList = new ArrayList<>();
        if(!commentedPostIdxList.isEmpty()) {
            for(int idx : commentedPostIdxList) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("userIdx", userIdx);
                paramMap.put("idx", idx);
                Post selectedPost = postMapper.findPostAndCommentByCommentUserIdx(paramMap);
                if(selectedPost == null) {
                    throw new NotFoundException("There's no Post idx"+idx);
                }
                commentedPostList.add(selectedPost);
                System.out.println("SelectedPost IDX="+ selectedPost.getIdx() + ", UserIDX ="+selectedPost.getUserIdx());
            }
            myActivityMap.put("commentedPostList", commentedPostList);
            System.out.println("commentSize"+commentedPostList.get(1).getComments().size());
        }

        return myActivityMap;
    }

    public User updateUserInfo(User user, MultipartFile file) throws Exception {
        int userIdx = user.getIdx();
        User findUser = userMapper.findByUserIdx(userIdx);
        if(findUser == null) {
            throw new Exception("Not Found User");
        }

        if(file != null) {
            updateProfileImage(userIdx, file);
        }
        userMapper.updateUserInfo(user);
        return userMapper.findByUserIdx(userIdx);
    }
}
