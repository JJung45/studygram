package com.studygram.mapper;

import com.studygram.domain.Post;
import com.studygram.domain.SearchResultObject;
import com.studygram.domain.Tag;
import com.studygram.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SearchMapper {
    // Top -> Post
    List<Post> searchPostList(String keyword);
    List<Tag> searchTagList(String keyword);
    List<User> searchAccountList(@Param("originKeyword") String originKeyword, @Param("newKeyword") String newKeyword);
}
