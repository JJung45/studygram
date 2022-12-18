package com.studygram.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    int idx;
    int userId;
    String userName;
    String content;
    List<PostTag> tags;
    List<Like> likes;
    List<Comment> comments;
    int likeCnt;
    boolean hasLiked;
    String topLiker;
    int commentCnt;
    Date createdDate;
    Date updatedDate;

    @Override
    public String toString() {
        return "Post{" +
                "idx=" + idx +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", likes=" + likes +
                ", comments=" + comments +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}