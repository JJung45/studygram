import React, { useState, useEffect, useRef } from "react";
import PostComment from "../main/PostCommentComponent";
import "../../styles/modal.css";
import "../../styles/Write.css";
import commentAPI from "../../lib/api/comment";
import moment from "moment";

const PostModal = (props) => {
  const { open, close, post } = props;
  const [comments, setComments] = useState(null);
  const [comment, setComment] = useState({
    postIdx: 0,
    content: "",
  });

  useEffect(() => {
    if (post && post.commentCnt > 0) {
      setComments(post.comments);
      setComment({
        ...comment,
        postIdx: post.idx,
      });
    }
  }, [post]);

  // 모달 영역 밖 클릭 시 닫기
  const node = useRef(null);
  const modalCloseHandler = (e) => {
    if (open && !node.current.contains(e.target)) {
      close();
    }
  };

  useEffect(() => {
    if (open) {
      window.addEventListener("click", modalCloseHandler);
    } else {
      window.removeEventListener("click", modalCloseHandler);
    }

    return () => {
      window.removeEventListener("click", modalCloseHandler);
    };
  }, [open]);

  const handleInput = (e) => {
    setComment({
      ...comment,
      content: e.target.value,
    });
  };

  const addComment = async () => {
    await commentAPI
      .addComment(comment)
      .then(async () => {
        await commentAPI.getCommentList(post.idx).then((result) => {
          setComments(result.data);
        });
      })
      .catch((err) => {
        console.log("Add New Comment Failed!", err);
      });

    // 댓글 입력창 reset
    setComment({
      ...comment,
      content: "",
    });
  };

  return (
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section className="postMoal-sec">
          <button className="close" onClick={() => !open}>
            &times;
          </button>
          <div className="postModal" ref={node} style={{ overflow: "auto" }}>
            <div className="file">
              {post.storePath && (
                <img
                  src={
                    post.storePath ??
                    "https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"
                  }
                  alt=""
                />
              )}
            </div>
            <div className="postContent">
              <div className="myProfile">
                <img src={post.profileImageUrl} alt="프로필이미지" />
                <div>
                  <span className="userID point-span">{post.userName}</span>
                </div>
              </div>
              <div className="content">
                <div className="image">
                  <img src={post.profileImageUrl} alt="프로필이미지" />
                </div>
                <div className="posting">
                  <span className="userID point-span">{post.userName}</span>
                  <div className="post-content"> {post.content} </div>
                </div>
              </div>
              <div className="comment-section">
                {comments?.map((comment) => (
                  <PostComment
                    data={comment}
                    key={comment.idx}
                    postUserIdx={post.userIdx}
                  ></PostComment>
                ))}
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
                <span>{moment(post.createdDate).format("YYYY-MM-DD")}</span>
              </div>
            </div>
          </div>
        </section>
      ) : null}
    </div>
  );
};

export default PostModal;
