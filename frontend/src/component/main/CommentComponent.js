import React, { Component, useEffect, useState } from "react";
import CommentApi from "../../lib/api/Comment";
import Comment from "../Comment";

const CommentComponent = () => {
  const [comments, setComments] = useState([]);

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


  const deleteComment = (commnetId) => {
    CommentApi.deleteComment(commnetId)
      .then((res) => {
        setComments({...res.data});
        console.log("Delete Comments: ", comments);
      })
      .catch((err) => {
        console.log("deleteComment() Error!", err);
      });
  };

  const editComment = (comment) => {
    CommentApi.updateComment(comment)
    .then((res) => {
      setComments({...res.data});
      console.log("Update Comments: ", comments);
    })
    .catch((err) => {
      console.log("editComment() Error!", err);
    })
  };

  return (
    <div>
      {comments && comments.map((comment) => (
        <Comment data={comment}></Comment>
      ))}
    </div>
  );
};

export default CommentComponent;
