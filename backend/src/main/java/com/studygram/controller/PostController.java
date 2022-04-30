package com.studygram.controller;

import com.studygram.domain.Post;
import com.studygram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(path = "/{postId}")
    public ResponseEntity addPost(@Valid @RequestBody Post post, HttpServletRequest request){
        postService.save(post);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

//    @PutMapping(path = "/{postId}")
//    public ResponseEntity<CollectionDTO> updateCollection(@Valid @RequestBody CollectionDTO collectionDTO){
//        CollectionDTO newCollection = collectionService.updateCollection(collectionDTO);
//        return ResponseEntity.ok(newCollection);
//    }

//    @DeleteMapping(path="/{postId}")
//    public void deleteCollection(@PathVariable(name="postId") String postId) {
//        collectionService.deleteCollection(postId);
//    }

    @GetMapping(path="/{postId}")
    public ResponseEntity getPost(@PathVariable(name = "postId") int postId){
        // TODO 댓글 + 사진 + 태그 연동 필요
        Post post = postService.findById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping(path = "/")
    public ResponseEntity getPosts(@RequestParam(required = false) HttpServletRequest request) {
        // TODO 페이징 필요
        // TODO 댓글 + 사진 + 태그 연동 필요
        List<Post> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

}
