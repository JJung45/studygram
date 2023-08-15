import React from "react";
import { Link } from "react-router-dom";
const Comment = ({ data }) => {
  return (
    <div className="content">
      <div className="image">
        <img src={data.profileImageUrl} alt="프로필이미지" />
      </div>
      <div className="posting">
        <span className="userID point-span">{data.userId}</span>
        <div className="post-content"> {data.content}</div>
      </div>
    </div>
  );
};

export default Comment;
