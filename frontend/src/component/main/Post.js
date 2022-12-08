import React, { useState } from "react";
import { Link } from "react-router-dom";
import PostComment from "./PostComment";
import PostApi from "../../lib/api/post";
import LikeApi from "../../lib/api/like";

const Post = ({ data }) => {
  const [post, setPost] = useState(data);

  const deleteLike = (data) => {
    const postId = data.idx;
    const cancle = LikeApi.cancle(postId).then(() => {
      reloadPost(postId);
    });
  };

  const saveLike = async (data) => {
    const postId = data.idx;
    const like = {
      postId: postId,
    };
    const save = await LikeApi.save(like).then(() => {
      reloadPost(postId);
    });
  };

  const reloadPost = async (postId) => {
    const newPost = await PostApi.getPost(postId).then((result) => {
      setPost(result.data);
    });
  };

  return (
    <article>
      <header>
        <div className="profile-of-article">
          <img
            className="img-profile pic"
            src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
            alt="minchoi님의 프로필 사진"
          />
          <span className="userID main-id point-span">{post.userName}</span>
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
          {!post.hasLiked && (
            <img
              className="icon-react"
              src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
              alt="heart_disLike"
              onClick={() => {
                saveLike(post);
              }}
            />
          )}
          {post.hasLiked && (
            <i
              className="ri-heart-3-fill ri-admin-line ri-xl"
              onClick={() => {
                deleteLike(post);
              }}
            ></i>
          )}
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
            <span className="point-span">외 {post.likeCnt}명</span>이 좋아합니다
          </p>
        </div>
        <div className="description">
          <p>
            <span className="point-span userID">{post.userName}</span>
            <span className="at-tag">{post.content}</span> 🌱
          </p>
        </div>
        {post.commentCnt != 0 && (
          <Link to={"/comment?postId=" + post.idx} state={{ data: post.idx }}>
            <span>View all {post.commentCnt} comments </span>
          </Link>
        )}
        <div className="comment-section">
          <div>
            {post.comments?.map((comment) => (
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
