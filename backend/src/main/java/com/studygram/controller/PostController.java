package com.studygram.controller;

import com.studygram.domain.Post;
import com.studygram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

//    @GetMapping(path="/{postId}")
//    public ResponseEntity<CollectionDTO> getCollection(@PathVariable(name = "postId") String postId){
//        CollectionDTO collectionDTO = collectionService.getCollectionById(postId);
//        return ResponseEntity.ok(collectionDTO);
//    }

//    @GetMapping(path = "/")
//    public ResponseEntity<Page<CollectionDTO>> getCollections(@PageableDefault(page = 0,size = 200) @RequestParam(required = false) Map<String,String> requestParams,
//                                                              Pageable pageable, HttpServletRequest request) {
//
//        Map<String, String> queries = new HashMap<>();
//        requestParams.forEach((key, value) -> {
//            if (!key.equals("page")
//                    && !key.equals("size")
//                    && !key.equals("query_set")
//                    && !key.equals("sort")) {
//                queries.put(key, value);
//            }
//        });
//        String token        = jwtUtil.extractTokenFromHeader(request);
//        String publisherIdFromToken  = jwtUtil.extractPublisherId(token);
//        String publisherIdFromParam  = queries.get("publisher_id");
//
//        if ( !publisherIdFromToken.equals(publisherIdFromParam) ) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        Page<CollectionDTO> collections = collectionService.getAllCollections(queries, pageable);
//        return ResponseEntity.ok(collections);
//    }

}
