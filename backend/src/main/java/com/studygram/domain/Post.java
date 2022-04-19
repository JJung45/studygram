package com.studygram.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Post {
    int idx;
    String userId;
    String imageUrlId;
    String content;
    String likeId;
    String tagId;
    String commentId;
    Date createdDate;
    Date updatedDate;

    public Post(
            int idx,
            String userId,
            String imageUrlId,
            String content,
            String likeId,
            String tagId,
            String commentId,
            Date createdDate,
            Date updatedDate
    ) {

        this.idx = idx;
        this.userId = userId;
        this.imageUrlId = imageUrlId;
        this.content = content;
        this.likeId = likeId;
        this.tagId = tagId;
        this.commentId = commentId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}