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
    int postIdx;
    int commentIdx;
    String content;
    int postCnt;
    Date createdDate;

    @Override
    public String toString() {
        return "Tag{" +
                "idx=" + idx +
                ", postIdx=" + postIdx +
                ", commentIdx=" + commentIdx +
                ", content='" + content + '\'' +
                ", postCnt='" + postCnt + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
