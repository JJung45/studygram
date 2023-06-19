package com.studygram.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultObject {
    List<Tag> tagList;
    List<User> userList;

}
