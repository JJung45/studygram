package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageUser {
    int idx;
    int imageIdx;
    int userIdx;
}
