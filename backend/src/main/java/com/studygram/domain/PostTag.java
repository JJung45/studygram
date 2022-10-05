package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTag {
    int idx;
    int postIdx;
    int tagIdx;
}

