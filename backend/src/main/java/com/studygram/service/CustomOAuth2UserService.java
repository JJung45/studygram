package com.studygram.service;

import com.studygram.common.oauth.ProviderType;
import com.studygram.common.oauth.RoleType;
import com.studygram.domain.OAuth2UserInfo;
import com.studygram.domain.OAuth2UserInfoFactory;
import com.studygram.domain.User;
import com.studygram.exception.OAuthProviderMissMatchException;
import com.studygram.mapper.UserMapper;
import com.studygram.utils.RandomStringGenerator;
import com.studygram.utils.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        System.out.println("GET USER ATTRIBUTES : "+user.getAttributes());
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        User savedUser = userMapper.findByClientId(userInfo.getId());

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                        " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("!!!!!!!!!!!"+userInfo.getName()+","+userInfo.getId()+","+userInfo.getEmail()+","+userInfo.getImageUrl());
        String emailId = userInfo.getEmail();
        int index = emailId.indexOf("@");
        if(index == -1)
            throw new IllegalArgumentException("Invalid Email ID.");

        String userName = emailId.substring(0, index);
        // user_name 중복검사 (이미 Provider Type은 검사)
        User savedUser = userMapper.findByUserName(userName);
        if(savedUser != null) {
            userName += RandomStringGenerator.getRandomString(3);
        }

        User user = new User(
                userName, // user_name -> full_name 기준으로 임의로 작성
                userInfo.getName(), // full_name
                userInfo.getEmail(), // email_id
                userInfo.getId(),
                providerType,
                RoleType.USER,
                userInfo.getImageUrl()
        );
        if(userMapper.save(user) < 1)
            return null;

        return userMapper.findByUserName(user.getUserName());
    }

    private User updateUser(User user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getFullName().equals(userInfo.getName())) {
            user.setFullName(userInfo.getName());
        }

        if (userInfo.getImageUrl() != null && !user.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.setProfileImageUrl(userInfo.getImageUrl());
        }

        return user;
    }

}

