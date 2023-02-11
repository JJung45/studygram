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
    int postIdx;
    int commentIdx;
    int userIdx;
    Date createdDate;

    @Override
    public String toString() {
        return "Like{" +
                "idx=" + idx +
                ", postId=" + postIdx +
                ", commentId=" + commentIdx +
                ", userId=" + userIdx +
                ", createdDate=" + createdDate +
                '}';
    }
}

