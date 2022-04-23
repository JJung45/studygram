package com.studygram.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Comment {
    int idx;
    int postId;
    int userId;
    String contents;
    List<String> tags;
    List<String> likes;
    Date createdDate;
    Date updatedDate;
}
