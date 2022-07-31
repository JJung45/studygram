import React, { Component, useEffect, useState } from "react";
import CommentApi from "../../lib/api/comment";
import Comment from "./PostComment";

const CommentComponent = (props) => {
  const postId = props.value;
  const [comments, setComments] = useState(null);
  this.state = {
    commentBox: [],
    comments: '',
  }

  const commentsData = () => {
    CommentApi.getComments(postId)
      .then((res) => {
        setComments(res.data);
        console.log("Get Comments: ", res.data);
      })
      .catch((err) => {
        console.log("getComments() Error!", err);
      });
  };

  useEffect(() => {
    commentsData();
  }, [postId]);

  /*
  useEffect(() => {
    CommentApi.getComments(12)
      .then((res) => {
        setComments({...res.data});
        console.log("Get Comments: ", comments);
      })
      .catch((err) => {
        console.log("getComments() Error!", err);
      });
  }, []); // 의존성 배열 비어있음
  */

  // 댓글입력값 State에 저장
  const [newComment, setNewComment] = useState('');
  const getInputValue = (e) => {
    setNewComment(e.target.value);
  }

  const addComment = (comment) => {
    CommentApi.addComment(comment)
    .then((res) => {
      setComments({...res.data});
        console.log("Delete Comments: ", comments);
    })
    .catch((err) => {
      console.log("deleteComment() Error!", err);
    });
  }

  const deleteComment = (commentId) => {
    CommentApi.deleteComment(commentId)
      .then((res) => {
        setComments(res.data);
        console.log("Delete Comments: ", comments);
      })
      .catch((err) => {
        console.log("deleteComment() Error!", err);
      });
  };

  const editComment = (comment) => {
    CommentApi.updateComment(comment)
    .then((res) => {
      setComments(res.data);
      console.log("Update Comments: ", comments);
    })
    .catch((err) => {
      console.log("editComment() Error!", err);
    })
  };

  return (
    <div>
      {comments?.map((comment) => (
        <ul></ul>
      ))}
      <form class="comment-input">

      <input
   className="instaPost_input"
   type="text"
   placeholder="댓글 달기..."
   value={newComment}
   onChange={this.getInputValue}  
   onKeyDown={this.enterComments}
 />
 <button onClick={this.uploadComments} className="submit">게시</button>
      </form>
    </div>
  );
};

export default CommentComponent;
