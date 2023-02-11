package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    int idx;
    int fromUserIdx;
    int toUserIdx;

    @Override
    public String toString() {
        return "Follow{" +
                "idx=" + idx +
                ", fromUser=" + fromUserIdx +
                ", toUser=" + toUserIdx +
                '}';
    }
}
