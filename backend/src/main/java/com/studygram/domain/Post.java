package com.studygram.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Post {
    int idx;
    int userId;
    String content;
    List<PostTag> tags;
    List<Like> likes;
    List<Comment> comments;
    int likeCnt;
    boolean hasLiked;
    int commentCnt;
    Date createdDate;
    Date updatedDate;

}