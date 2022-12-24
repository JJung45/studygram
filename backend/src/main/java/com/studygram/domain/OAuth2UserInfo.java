package com.studygram.domain;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId(); // User Table에서 user_name 항목

    public abstract String getName(); // User Table에서 full_name 항목

    public abstract String getEmail(); // User Table에서 emailAddr 항목

    public abstract String getImageUrl();
}
