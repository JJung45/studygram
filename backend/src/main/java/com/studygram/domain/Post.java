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
    Date createdDate;
    Date updatedDate;

}