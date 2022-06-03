import React from "react";

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
          <span className="userID main-id point-span">{data.id}</span>
        </div>
        <img
          className="icon-react icon-more"
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/more.png"
          alt="more"
        />
      </header>
      <div className="main-image">
        <img
          src="https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"
          alt="minchoi님의 피드 사진"
          className="mainPic"
        />
      </div>
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
            <span className="point-span">외 2,412,751명</span>이 좋아합니다
          </p>
        </div>
        <div className="description">
          <p>
            <span className="point-span userID">minchoi</span>
            <span className="at-tag">@test @react</span> 🌱
          </p>
        </div>
        <div className="comment-section">
          <ul className="comments">
            <li>
              <span>
                <span className="point-span userID">test2</span>딤섬 맛있었다
              </span>
              <img
                className="comment-heart"
                src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
                alt="하트"
              />
            </li>
          </ul>
          <div className="time-log">
            <span>32분 전</span>
          </div>
        </div>
      </div>
      <div className="hl"></div>
      <div className="comment">
        <input
          id="input-comment"
          className="input-comment"
          type="text"
          placeholder="댓글 달기..."
        />
        <button type="submit" className="submit-comment" disabled>
          게시
        </button>
      </div>
    </article>
  );
};

export default Post;
