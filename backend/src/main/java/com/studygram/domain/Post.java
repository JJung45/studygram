package com.studygram.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Post {

    long idx;
    String userId;
    String imageUrlId;
    String content;
    String likeId;
    String tagId;
    String commentId;
    Date createdDate;
    Date updatedDate;

}