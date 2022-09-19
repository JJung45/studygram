import React from "react";

const Comment = ({ data }) => {
  return (
    <ul className="comments">
      <li key={data.idx}>
        <span>
          <span class="point-span userID">{data.userName}</span>
          {data.content}
        </span>
        <img
          class="comment-heart"
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
          alt="하트"
        />
      </li>
    </ul>
  );
};

export default Comment;
