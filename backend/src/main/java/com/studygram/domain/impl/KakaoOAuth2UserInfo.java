package com.studygram.domain.impl;

import com.studygram.domain.OAuth2UserInfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }
    // kakao는 kakao_account에 유저정보가 있다. (email)
    private Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
    // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
    private Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        if (kakaoAccount == null) {
            return null;
        }

        return (String) kakaoProfile.get("nickname");
    }

    @Override
    public String getEmail() {
        if (kakaoAccount == null) {
            return null;
        }

        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        if (kakaoAccount == null) {
            return null;
        }

        return (String) kakaoProfile.get("profile_image_url");
    }
}
