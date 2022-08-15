package com.studygram.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowMapper {
    int countFollower(int userIdx);
    int countFollowings(int userIdx);
    List<Integer> getFollowers(int userIdx);
    List<Integer> getFollowings(int userIdx);
}
