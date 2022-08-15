package com.studygram.domain;

public class Follow {
    int idx;
    User fromUser;
    User toUser;

    @Override
    public String toString() {
        return "Follow{" +
                "idx=" + idx +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                '}';
    }
}
