import React, { useState,useEffect } from "react";
import { Link } from "react-router-dom";

import PostApi from "../../lib/api/post";
import LikeApi from "../../lib/api/like";

import LikeModal from "./LikeModal";
import {convertContentTag} from "../../module/utils/convertContentTag";
import commentAPI from "../../lib/api/comment";
import moment from "moment";

const Post = ({ data }) => {
  const [post, setPost] = useState(data);
  const [modalOpen, setModalOpen] = useState(false);
  const [likers, setLikers] = useState(null);
  const [comments, setComments] = useState(null);
  const [comment, setComment] = useState({
    content: "",
  });
  // const postTags = data.tags.length > 0 ? data.tags : [];

  const deleteLike = (data) => {
    const postIdx = data.idx;
    const cancle = LikeApi.cancle(postIdx).then(() => {
      reloadPost(postIdx);
    });
  };

  const saveLike = async (data) => {
    const postIdx = data.idx;
    const like = {
      postIdx: postIdx,
    };
    const save = await LikeApi.save(like).then(() => {
      reloadPost(postIdx);
    });
  };

  const reloadPost = async (postIdx) => {
    const newPost = await PostApi.getPost(postIdx).then((result) => {
      setPost(result.data);
    });
  };

  const likeMessage = (post) => {
    const topLiker = post.topLiker;
    const likeCnt = post.likeCnt;

    if (topLiker == null) {
      return ``;
    } else if (topLiker != null && likeCnt == 1) {
      return `${topLiker}님이 좋아합니다`;
    } else {
      return `${topLiker}님 외 ${likeCnt - 1}명이 좋아합니다`;
    }
  };

  const openModal = async (post) => {
    setModalOpen(true);
    await LikeApi.users(post.idx).then((likers) => {
      setLikers(likers);
    });
  };
  const closeModal = () => {
    setModalOpen(false);
  };

  const handleInput = (e) => {
    setComment({
      content: e.target.value,
    });
  };

  const addComment = async () => {
    await commentAPI
      .addComment({
        postIdx: post.idx,
        content: comment.content,
      })
      .then(async () => {
        await commentAPI.getComments(post.idx).then((result) => {
          setComments(result.data);
        });
      })
      .catch((err) => {
        console.log("Add New Comment Failed!", err);
      });
  };
  
 const getTimeDifference = (post) => {
    const diffInMinutes =  moment().diff(moment(post.createdDate), "minutes");
    const diffInHours =  moment().diff(moment(post.createdDate), "hours");
    const diffInDays =  moment().diff(moment(post.createdDate), "days");
    const humanizedTimeDiff =
      diffInMinutes < 60
        ? `${diffInMinutes}분 전`
        : diffInHours >= 24
        ? `${diffInDays}일 전`
        : `${diffInHours}시간 전`;

    return humanizedTimeDiff;
  }

  return (
    <article>
      <header>
        <div className="profile-of-article">
          <img
            className="img-profile pic"
            src={post.profileImageUrl}
            alt={post.userName + "님의 프로필 사진"}
          />
          <span className="userID main-id point-span">
            <a href={`/${post.userName}/`}>{post.userName}</a>
          </span>
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
            src={post.storePath}
            alt={post.userName + "님의 피드 사진"}
            className="mainPic"
          />
        </div>
      </Link>
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
        {
           likeMessage(post) && (
              <div className="reaction">
              <div className="liked-people">
                <img
                  className="pic"
                  src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                  alt="test님의 프로필 사진"
                />
                <span
                onClick={(event) => {
                  openModal(post, event);
                }}
              >
                {likeMessage(post)}
              </span>
              </div>
              <LikeModal
                open={modalOpen}
                close={closeModal}
                header="좋아요"
                likers={likers}
              ></LikeModal>
            </div>
            )
          }
          {data.commentCnt !== 0 && (
          <Link to={"/comment?postId=" + data.idx} state={{ data: data.idx }}>
            <span>View all {data.commentCnt} comments </span>
          </Link>
        )}
        <div className="description">
          <p>
            <span className="point-span userID">
              {data.userName ?? "anoymous"}
            </span>
            {/*<span className="at-tag">{data.content}</span>*/}
            {convertContentTag(data.content, data.tags)}
          </p>
        </div>
        
              <div>
                <input
                  className="comments-header-textarea"
                  id="content"
                  type="text"
                  value={comment.content}
                  onChange={handleInput}
                  placeholder="댓글을 입력하세요"
                />
                <button onClick={addComment}>입력</button>
                </div>
      <div className="time-log">
      <span>{ getTimeDifference(post) }</span>
    </div>
    </article>
  );
};

export default Post;
