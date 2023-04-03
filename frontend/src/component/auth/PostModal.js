import React, { useState, useEffect } from "react";
import PostComment from "../main/PostCommentComponent";
import "../../styles/modal.css";
import "../../styles/Write.css";
import commentAPI from "../../lib/api/comment";

const PostModal = (props) => {
  const { open, close, post } = props;
  const [comments, setComments] = useState(null);
  const [comment, setComment] = useState({
    content: "",
  });

  useEffect(() => {
    if (post != null) {
      setComments(post.comments);
    }
  });
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

  return (
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section>
          <button className="close" onClick={close}>
            &times;
          </button>
          <main className="postModal">
            <div className="file">
              <div
                className="imageSelect"
                style={{
                  alignItems: "center",
                  justifyContent: "center",
                  height: "100%",
                }}
              >
                이미지가 있다고 치고
              </div>
              <div style={{ height: "100%", paddingTop: "0" }}>
                {/* {fileImage && (
                    <div
                      style={{
                        backgroundRepeat: "no-repeat",
                        backgroundSize: "cover",
                        height: "100%",
                      }}
                    ></div>
                  )} */}
              </div>
            </div>
            <div className="postContent">
              <div className="myProfile">
                <img
                  className="pic"
                  src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                  alt="minchoi 프로필 사진"
                />
                <div>
                  <span className="userID point-span">{post.userName}</span>
                </div>
              </div>
              <div>
                <span className="userId point-span">{post.userName}</span>
                &nbsp;
                {post.content}
              </div>
              <hr />
              <div className="comment-section">
                <div>
                  {comments?.map((comment) => (
                    <PostComment data={comment}></PostComment>
                  ))}
                </div>
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
              <hr />
              <div>
                <div className="time-log">
                  <span>{post.createdDate}</span>
                </div>
              </div>
            </div>
          </main>
        </section>
      ) : null}
    </div>
  );
};

export default PostModal;
