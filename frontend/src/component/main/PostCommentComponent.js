import React from "react";
import { Link } from "react-router-dom";
const Comment = ({ data }) => {
  console.log(data); //TODO findCommentsByPostIdx 추가정보 가져오기
  return (
    <div className="content">
      <div className="image">
        <img src="" alt="프로필이미지" />
      </div>
      <div className="posting">
        <span className="userID point-span">{data.userId}</span>
        <div className="post-content"> {data.content}</div>
      </div>
    </div>
  );
};

export default Comment;
