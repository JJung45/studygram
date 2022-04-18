package com.studygram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    @PostMapping(path = "/{postId}")
//    public ResponseEntity<CollectionDTO> addCollection(@Valid @RequestBody CollectionDTO collectionDTO, HttpServletRequest request){
//        String token        = jwtUtil.extractTokenFromHeader(request);
//        String publisherIdFromToken  = jwtUtil.extractPublisherId(token);
//        String publisherIdFromParam  = collectionDTO.getPublisherId();
//
//        if ( !publisherIdFromToken.equals(publisherIdFromParam) ) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(collectionService.addCollection(collectionDTO),
//                HttpStatus.CREATED);
//    }

    @PutMapping(path = "/{postId}")
//    public ResponseEntity<CollectionDTO> updateCollection(@Valid @RequestBody CollectionDTO collectionDTO){
//        CollectionDTO newCollection = collectionService.updateCollection(collectionDTO);
//        return ResponseEntity.ok(newCollection);
//    }

    @DeleteMapping(path="/{postId}")
//    public void deleteCollection(@PathVariable(name="postId") String postId) {
//        collectionService.deleteCollection(postId);
//    }

    @GetMapping(path="/{postId}")
//    public ResponseEntity<CollectionDTO> getCollection(@PathVariable(name = "postId") String postId){
//        CollectionDTO collectionDTO = collectionService.getCollectionById(postId);
//        return ResponseEntity.ok(collectionDTO);
//    }

    @GetMapping(path = "/")
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
