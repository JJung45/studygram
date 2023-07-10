package com.studygram.domain;

public enum NotificationType {
    LIKE("좋아요", "회원님의 글을 좋아합니다."),
    FOLLOW("팔로우", "회원님을 팔로우합니다."),
    COMMENT("덧글", "회워님의 글에 댓글을 달았습니다."),
    DUMMY("더미", "더미입니다.");

    private String type;
    private String message;

    NotificationType(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
