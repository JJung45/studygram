package com.studygram.service;

import com.studygram.common.ApiResponse;
import com.studygram.common.SimplePageRequest;
import com.studygram.domain.Comment;
import com.studygram.domain.NotificationType;
import com.studygram.domain.User;
import com.studygram.mapper.CommentMapper;
import com.studygram.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentService {
    private final CommentMapper commentMapper;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    public CommentService(CommentMapper commentMapper, PostService postService) {
        this.commentMapper = commentMapper;
        this.postService = postService;
    }

    public List<Comment> getCommentsListWithPaging(int postIdx, SimplePageRequest simplePageRequest) {
        int limit = simplePageRequest.getLimit();
        long offset = simplePageRequest.getOffset();
        return commentMapper.findCommentsByPostIdxWithPaging(postIdx, limit, offset);
    }

    public List<Comment> getCommentsListByPostId(int postIdx) {
        return commentMapper.findCommentsByPostIdx(postIdx);
    }

    public Comment getCommentByCommentId(int commentIdx) { return commentMapper.findByCommentIdx(commentIdx);}

    public int getCommentCntByPostId(int postIdx) { return commentMapper.getCommentCntByPostIdx(postIdx); }

    public void createComment(Comment comment) {
        // 1. Post 데이터 있는지 확인
        if(postService.findById(comment.getPostIdx()) == null) {
            ApiResponse.fail();
        }

        /*
            2. 댓글 내용에서 Tag 추출하고 Insert
        String content = comment.getContent();
        List<String> tags = StringUtil.getTagsFromContent(content);
        for(String str : tags) {
            Tag tag = new Tag();
            tag.setContents(str);

            if(tagService.save(tag) < 0) {
                ApiResponse.fail();
            }
        }
        */

        // UserID 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUser(userDetails.getUsername());
        comment.setUserIdx(user.getIdx());
        comment.setUserName(user.getUserName());

        if(commentMapper.save(comment) < 0) {
            ApiResponse.fail();
        }

        notificationService.send(user.getIdx(), user.getIdx(), comment.getIdx(), NotificationType.COMMENT);
    }

    public void updateComment(Comment comment) {
        Comment originComment = commentMapper.findByCommentIdx(comment.getIdx());
        if (originComment == null) {
            log.error("Can't find Comment!");
            ApiResponse.notFoundFail(); // return 예외처리?
        } else {
            originComment.setContent(comment.getContent());
            originComment.setUpdatedDate(TimeUtil.getCurrentTime());
            System.out.println("originCom:"+originComment.toString());
            if (commentMapper.update(originComment) < 0) {
                System.out.println("Update 완료");
                ApiResponse.fail();
            }
        }

    }

    public void deleteCommentByCommentId(Integer commentIdx) {
        if(commentMapper.findByCommentIdx(commentIdx) == null) {
            log.error("Can't find Comment!");
            ApiResponse.notFoundFail();
        }

        if(commentMapper.deleteByCommentIdx(commentIdx) < 0) {
            ApiResponse.fail();
        }
    }


}
