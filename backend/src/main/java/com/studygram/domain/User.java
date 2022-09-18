package com.studygram.domain;

import com.studygram.common.oauth.ProviderType;
import com.studygram.common.oauth.RoleType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int idx;
    String userName;
    String fullName;
    String passwd;
    String phoneNum;
    String emailId;
    String clientId;
    ProviderType providerType;
    RoleType roleType;
    String profileImageUrl;
    int followingCnt;
    List<Integer> following;
    int followersCnt;
    List<Integer> followers;
    Date createdDate;
    Date lastLoginDate;

    // 소셜로그인 시 사용할 생성자
    public User(String userName, String fullName, String emailId, String clientId, ProviderType providerType, RoleType roleType, String profileImageUrl) {
        this.userName = userName;
        this.fullName = fullName;
        this.emailId = emailId;
        this.clientId = clientId;
        this.providerType = providerType;
        this.roleType = roleType;
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "idx=" + idx +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", passwd='" + passwd + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", emailId='" + emailId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", providerType=" + providerType +
                ", roleType=" + roleType +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", followingCnt=" + followingCnt +
                ", following=" + following +
                ", followersCnt=" + followersCnt +
                ", followers=" + followers +
                ", createdDate=" + createdDate +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }
}
