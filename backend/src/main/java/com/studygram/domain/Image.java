package com.studygram.domain;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    Long idx;
    String originalFilename;
    String storePath;
    Date createdDate;
    Post post;
}
