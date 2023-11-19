package com.studygram.mapper;

import com.studygram.domain.Comment;
import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PostMapper {
    int save(Post post);
    Post findById(int id);
    Post findByIds(@Param("postIdx") Integer postIdx, @Param("userIdx") Integer userIdx);
    List<Post> findByUserInfo(@Param("clientId") String clientId, @Param("userName") String userName);
    List<Post> findAll(@Param("limit") @Nullable Integer limit, @Param("offset") @Nullable Integer offset, @Nullable @Param("userIdx") Integer userIdx);
    List<PostTag> findPostTags(int postIdx);
    void update(Post post);
    void delete(Post post);
    List<Post> findByManyIds(@Param("postTagIds") List<Integer> postTagIds);
    int countPostsByUserName(String userName);
    Post findSortByIdAsc();
    List<Integer> findPostIdxByLikeUserIdx(int userIdx);
    Post findPostAndCommentByCommentUserIdx(Map<String, Object> paramMap);

}
