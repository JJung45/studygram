package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.User;
import com.studygram.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    public int save(Post post) {
        postMapper.save(post);
        List<String> tagList = tagService.saveTags(post);

        for(String tagContent : tagList) {
            String newContent = post.getContent().replace(tagContent, "<a link=''>"+tagContent+"</a>");
            post.setContent(newContent);

            postMapper.update(post);
        }

        return post.getIdx();
    }

    public Post findById(int postId) {
        User user = userService.getUser();
        return postMapper.findByIds(postId, user.getIdx());
    }

    public Post findByIds(int postId, int userId) {
        return postMapper.findByIds(postId, userId);
    }

    public List<Post> findAll(Integer limit, Integer offset) {
        User user = userService.getUser();
        return postMapper.findAll(limit, offset, user.getIdx());
    }

    public Post update(Post post) {
        // TODO 댓글, 좋아요, 태그 연결해서 업데이트
        postMapper.update(post);
        return post;
    }

    public void delete(Post post) {
        postMapper.delete(post);
    }
}
