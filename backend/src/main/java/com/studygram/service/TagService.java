package com.studygram.service;

import com.studygram.domain.Tag;
import com.studygram.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public int save(Tag tag) {
        return tagMapper.save(tag);
    }

    public ArrayList<Tag> findByContents(ArrayList<String> tagContents) {
        return tagMapper.findByContents(tagContents);
    }

    public int countAll() {
        return tagMapper.countAll();
    }

    public void delete(ArrayList<Integer> tagIdxes) {
        tagMapper.delete(tagIdxes);
    }
}
