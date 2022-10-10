package com.studygram.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    int idx;
    int postId;
    int commentId;
    int userId;
    String userName;
    Date createdDate;

    @Override
    public String toString() {
        return "Like{" +
                "idx=" + idx +
                ", postId=" + postId +
                ", commentId=" + commentId +
                ", userId=" + userId +
                ", createdDate=" + createdDate +
                '}';
    }
}

