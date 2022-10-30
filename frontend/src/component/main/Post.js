import React from "react";
import {Link} from 'react-router-dom'
import PostComment from "./PostComment";

// const onChange

// const onChange

const Post = ({ data }) => {


  return (
    <article>
      <header>
        <div className="profile-of-article">
          <img
            className="img-profile pic"
            src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
            alt="minchoi님의 프로필 사진"
          />
          <span className="userID main-id point-span">{data.userName}</span>
        </div>
        <img
          className="icon-react icon-more"
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/more.png"
          alt="more"
        />
      </header>
    <Link to={"/post/" + data.idx} state={data}>
      <div className="main-image">
        <img
          src="https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"
          alt="minchoi님의 피드 사진"
          className="mainPic"
        />
      </div>
      </Link>
      <div className="icons-react">
        <div className="icons-left">
          <img
            className="icon-react"
            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
            alt="하트"
          />
          <img
            className="icon-react"
            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/comment.png"
            alt="말풍선"
          />
          <img
            className="icon-react"
            src="https://cdn3.iconfinder.com/data/icons/email-133/32/Email_paper_air_plane_airplane_send_message-512.png"
            alt="DM"
          />
        </div>
        <img
          className="icon-react"
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/bookmark.png"
          alt="북마크"
        />
      </div>
      <div className="reaction">
        <div className="liked-people">
          <img
            className="pic"
            src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
            alt="test님의 프로필 사진"
          />
          <p>
            <span className="point-span">test</span>님{" "}
            <span className="point-span">외 {data.likeCnt}명</span>이 좋아합니다
          </p>
        </div>
        <div className="description">
          <p>
            <span className="point-span userID">{data.userName ?? "anoymous"}</span>
            <span className="at-tag">{data.content}</span>
          </p>
        </div>
        {data.commentCnt != 0 &&
        <Link to={"/comment?postId=" + data.idx} state={{data: data.idx}}>
          <span>View all {data.commentCnt} comments </span>
        </Link>
        }
        <div className="comment-section">
          <div>
            {data.comments?.map((comment) => (
              <PostComment data={comment}></PostComment>
            ))}
          </div>
          <div className="time-log">
            <span>32분 전</span>
          </div>
        </div>
      </div>
      <div className="hl"></div>
    </article>
  );
};

export default Post;
