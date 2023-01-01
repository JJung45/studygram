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
    String emailAddr;
    String clientId;
    ProviderType providerType;
    RoleType roleType;
    String profileImageUrl;
    // 프로필 메시지
    String profileMsg;
    int followingCnt;
    List<Integer> following;
    int followersCnt;
    List<Integer> followers;
    // 검색 기록 List
    List<String> searchList;
    // 계정 공개 / 비공개
    String publicType;
    Date createdDate;
    Date lastLoginDate;

    // 소셜로그인 시 사용할 생성자
    public User(String userName, String fullName, String emailAddr, String clientId, ProviderType providerType, RoleType roleType, String profileImageUrl) {
        this.userName = userName;
        this.fullName = fullName;
        this.emailAddr = emailAddr;
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
                ", emailAddr='" + emailAddr + '\'' +
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
