package com.studygram.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    int idx;
    int postId;
    int commentId;
    String content;
    Date createdDate;

    @Override
    public String toString() {
        return "Tag{" +
                "idx=" + idx +
                ", postId=" + postId +
                ", commentId=" + commentId +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

