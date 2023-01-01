package com.studygram.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    int idx;
    int postIdx;
    int commentIdx;
    String content;
    Date createdDate;
}
