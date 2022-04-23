package com.studygram.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Post {
    long idx;
    int userId;
    int imageUrlId;
    String content;
    int likesId;
    int tagsId;
    int commentsId;
    Date createdDate;
    Date updatedDate;

}