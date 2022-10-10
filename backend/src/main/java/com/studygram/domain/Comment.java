package com.studygram.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
/*
 * Mapper.xml 에서 Collection Index 에러 나오는것 해결하기 위해ㅑ서
 * All/No Args 어노테이션 필수... 왜?... 대체...?
 * Builder가 내부에서
 * */
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    int idx;
    int postId;
    int userId;
    String userName;
    String content;
    List<Tag> tags;
    List<Like> likes;
    Date createdDate;
    Date updatedDate;

    @Override
    public String toString() {
        return "Comment{" +
                "idx=" + idx +
                ", postId=" + postId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
