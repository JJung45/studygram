package com.studygram.mapper;

import com.studygram.domain.Follow;
import com.studygram.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowMapper {
    int saveFollow(Follow follow);
    int saveUnfollow(Follow follow);
    Follow getFollowInfo(Follow follow);
    int countFollowers(int userIdx);
    int countFollowings(int userIdx);
    List<Integer> getFollowers(int userIdx);
    List<Integer> getFollowings(int userIdx);
    List<User> getSuggestions(int userIdx);
}
