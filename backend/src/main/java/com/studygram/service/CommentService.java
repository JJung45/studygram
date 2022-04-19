package com.studygram.service;

import com.studygram.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    CommentMapper commentMapper;
}
