import React from "react";
import { Link } from "react-router-dom";
import CommentApi from "../../lib/api/comment";
import moment from "moment";
import LikeApi from "../../lib/api/like";

const Comment = ({ data }) => {
  const saveLike = async (target, hasLike, data) => {
    const commentIdx = data.idx;
    const commentUserIdx = data.userIdx;
    const like = {
      commentIdx: commentIdx,
      commentUserIdx: commentUserIdx,
    };
    const result = await LikeApi.save(like);

    if (result.status == 200) {
      target.innerHTML = target.innerHTML === '♥' ? '♡' : '♥';
    }
  };

  const hasCommentLike = (data) => {
      return data.likes.some(like => like.commentIdx == data.idx)
  }

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
      { (data.likes.length === 0 || !hasCommentLike(data)) ?
           <span className="heart" onClick={(e) => {saveLike(e.currentTarget, hasCommentLike(data), data);}}> ♡</span>
        :  <span className="heart" onClick={(e) => {saveLike(e.currentTarget, hasCommentLike(data), data);}}> ♥ </span>
      }
    </div>
  );
};

export default Comment;
