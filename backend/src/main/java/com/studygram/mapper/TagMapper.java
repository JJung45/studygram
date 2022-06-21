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
    Tag findContent(String search);
    ArrayList<Tag> findSimilarContent(String search);
}
