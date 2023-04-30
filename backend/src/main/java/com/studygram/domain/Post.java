package com.studygram.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    int idx;

    int userIdx;

    String userName;

    String profileImageUrl;

    String content;

    List<PostTag> tags;

    List<Like> likes;

    int likeCnt;

    boolean hasLiked;

    String topLiker;

    List<Comment> comments;

    int commentCnt;

    String storePath;

    Date createdDate;

    Date updatedDate;
}