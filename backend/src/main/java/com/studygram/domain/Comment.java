package com.studygram.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
/*
 * Mapper.xml 에서 Collection Index 에러 나오는것 해결하기 위해서
 * All/No Args 어노테이션 필수... 왜?... 대체...?
 * Builder가 내부에서
 * */
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    Integer idx;
    int userIdx;
    int postIdx;
    String content;
    String userName;
    String profileImageUrl;
    List<Tag> tags;
    List<Like> likes;
    Date createdDate;
    Date updatedDate;

    @Override
    public String toString() {
        return "Comment{" +
                "idx=" + idx +
                ", postId=" + postIdx +
                ", userId=" + userIdx +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
