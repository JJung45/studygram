import React from "react";
import { Link } from "react-router-dom";
import CommentApi from "../../lib/api/comment";
import moment from "moment";

const Comment = ({ data }) => {

  const getTimeDifference = (data) => {
    const diffInMinutes =  moment().diff(moment(data.createdDate), "minutes");
    const diffInHours =  moment().diff(moment(data.createdDate), "hours");
    const diffInDays =  moment().diff(moment(data.createdDate), "days");
    const humanizedTimeDiff =
      diffInMinutes < 60
        ? `${diffInMinutes}분 전`
        : diffInHours >= 24
        ? `${diffInDays}일 전`
        : `${diffInHours}시간 전`;
  
    return humanizedTimeDiff;
  }

  return (
    <div className="content">
      <div className="image">
        <img src={data.profileImageUrl} alt="프로필이미지" />
      </div>
      <div className="posting">
        <div>
          <span className="userID point-span">{data.userName}</span>
          <div className="time">
            { getTimeDifference(data) }
          </div>
        </div>
        <div className="post-content"> {data.content}</div>
      </div>
      <span className="heart">
        ♡
      </span>
    </div>
  );
};

export default Comment;
