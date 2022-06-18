package com.studygram.mapper;

import com.studygram.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
@Repository
public interface TagMapper {
    int save(Tag tag);
    ArrayList<Tag> findByContents(ArrayList<String> contents); // TODO xml 수정. resultMap 오류
    void delete(ArrayList<Integer> tagIndexes); // TODO xml 수정. resultMap 오류
    int countAll();
}
