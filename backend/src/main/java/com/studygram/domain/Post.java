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
    String content;
    List<PostTag> tags;
    List<Like> likes;
    List<Comment> comments;
    Date createdDate;
    Date updatedDate;

}