package com.studygram.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
    String content;
    List<PostTag> tags;
    List<Like> likes;
    int likeCnt;
    boolean hasLiked;
    List<Comment> comments;
    int commentCnt;
    List<Attachment> attachedFiles;

    List<MultipartFile> imageFiles;

    List<MultipartFile> generalFiles;

    Date createdDate;
    Date updatedDate;

    @Override
    public String toString() {
        return "Post{" +
                "idx=" + idx +
                ", userId=" + userIdx +
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