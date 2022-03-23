package com.studygram.mapper;

import com.studygram.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User findByUserName(String userName);
    User findByEmailId(String emailId);
    int save(User user);
    List<User> selectAll();

    int updateUser(User user);

}
