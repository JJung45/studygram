package com.studygram.mapper;

import com.studygram.domain.Post;
import com.studygram.domain.Tag;
import com.studygram.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SearchMapper {
    // Top -> Post
    List<Integer> searchPostList(String keyword);
    List<Tag> searchTagList(String keyword);
    List<Integer> searchAccountList(String keyword);
}
