package com.studygram.service;

import com.studygram.domain.Post;
import com.studygram.domain.PostTag;
import com.studygram.domain.Tag;
import com.studygram.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private PostTagService postTagService;

    public void saveTags(Post post) {
        String content = post.getContent();
        String[] arr = content.split(" ");
        List<String> tagList = Arrays.stream(arr)
                .filter(string -> string.contains("#"))
                .collect(Collectors.toList());

        // 게시물이 저장된 다음에 가져와야할듯!
        int postIdx = post.getIdx();
        for(String tagContent : tagList) {
            String[] newTagList = tagContent.split("#");

            for(String newTagContent : newTagList) {
                if (newTagContent.equals("")) {
                    continue;
                }

                Tag tags = tagMapper.findContent(newTagContent);
                if (tags == null) {
                    tags = Tag
                            .builder()
                            .contents(newTagContent)
                            .build();
                    tagMapper.save(tags);
                }

                postTagService.saveTagPost(post, tags);
            }
        }

    }

    public void updateTagsByPost(Post post) {
        postTagService.deleteTagsByPost(post);
        saveTags(post);
    }

    //검색(태그클릭)
    public List<Post> findPostsByTag(String search) {
        Tag tag = tagMapper.findContent(search);
        List<PostTag> postTags = postTagService.findPostTagsByTag(tag);
        return postService.findByManyIds(postTags);
    }

    //자동완성
    public ArrayList<Tag> findTags(String search) {
        return tagMapper.findSimilarContent(search);
    }
}
