import React from "react";

const Feed = ({ data }) => {
  return (
    <article>
      <header>
        <div class="profile-of-article">
          <img
            class="img-profile pic"
            src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
            alt="minchoi님의 프로필 사진"
          />
          <span class="userID main-id point-span">{data.id}</span>
        </div>
        <img
          class="icon-react icon-more"
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/more.png"
          alt="more"
        />
      </header>
      <div class="main-image">
        <img
          src="https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"
          alt="minchoi님의 피드 사진"
          class="mainPic"
        />
      </div>
      <div class="icons-react">
        <div class="icons-left">
          <img
            class="icon-react"
            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
            alt="하트"
          />
          <img
            class="icon-react"
            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/comment.png"
            alt="말풍선"
          />
          <img
            class="icon-react"
            src="https://cdn3.iconfinder.com/data/icons/email-133/32/Email_paper_air_plane_airplane_send_message-512.png"
            alt="DM"
          />
        </div>
        <img
          class="icon-react"
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/bookmark.png"
          alt="북마크"
        />
      </div>
      <div class="reaction">
        <div class="liked-people">
          <img
            class="pic"
            src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
            alt="test님의 프로필 사진"
          />
          <p>
            <span class="point-span">test</span>님{" "}
            <span class="point-span">외 2,412,751명</span>이 좋아합니다
          </p>
        </div>
        <div class="description">
          <p>
            <span class="point-span userID">minchoi</span>
            <span class="at-tag">@test @react</span> 🌱
          </p>
        </div>
        <div class="comment-section">
          <ul class="comments">
            <li>
              <span>
                <span class="point-span userID">test2</span>딤섬 맛있었다
              </span>
              <img
                class="comment-heart"
                src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
                alt="하트"
              />
            </li>
          </ul>
          <div class="time-log">
            <span>32분 전</span>
          </div>
        </div>
      </div>
      <div class="hl"></div>
      <div class="comment">
        <input
          id="input-comment"
          class="input-comment"
          type="text"
          placeholder="댓글 달기..."
        />
        <button type="submit" class="submit-comment" disabled>
          게시
        </button>
      </div>
    </article>
  );
};

export default Feed;
