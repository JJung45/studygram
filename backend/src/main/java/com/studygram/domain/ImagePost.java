package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagePost {
    int idx;
    int imageIdx;
    int postIdx;
}
