package com.studygram.domain;

import com.studygram.common.oauth.ProviderType;
import com.studygram.common.oauth.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int idx;
    String userName;
    String fullName;
    String passwd;
    String phoneId;
    String emailId;
    String clientId;
    ProviderType providerType;
    RoleType roleType;
    Date createdDate;
    Date lastLoginDate;

//    public User(String userName, String fullName, String passwd, String emailId, ProviderType providerType, RoleType roleType) {
//        this.userName = userName;
//        this.fullName = fullName;
//        this.passwd = passwd;
//        this.emailId = emailId;
//        this.providerType = providerType;
//        this.roleType = roleType;
//    }

    // 소셜로그인 시 사용할 생성자
    public User(String userName, String fullName, String emailId, String clientId, ProviderType providerType, RoleType roleType) {
        this.userName = userName;
        this.fullName = fullName;
        this.emailId = emailId;
        this.clientId = clientId;
        this.providerType = providerType;
        this.roleType = roleType;
    }
}
