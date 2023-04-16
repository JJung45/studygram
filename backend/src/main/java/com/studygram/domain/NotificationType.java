package com.studygram.domain;

public enum NotificationType {
    LIKE("좋아요", "좋아합니다."),
    FOLLOW("팔로우", "팔로우합니다."),
    COMMENT("덧글", "달았습니다.");

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
