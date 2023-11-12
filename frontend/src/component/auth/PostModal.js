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
        <div className={open ? "openModal modal" : "modal"}>
            {open ? (
                <section className="postMoal-sec">
                    <button className="close" onClick={()=>!open}>
                        &times;
                    </button>
                    <div className="postModal" ref={node}>
                        <div className="file">
                            {post.storePath && (
                                <img
                                    src={post.storePath ?? "https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"}
                                    alt=""
                                />
                            )}
                        </div>
                        <div className="postContent">
                            <div className="myProfile">
                                <img
                                    src={post.profileImageUrl}
                                    alt="프로필이미지"
                                />
                                <div>
                                    <span className="userID point-span">{post.userName}</span>
                                </div>
                            </div>
                            <div className="content-comment">
                            <div className="content">
                                <div className="image">
                                    <img
                                        src={post.profileImageUrl}
                                        alt="프로필이미지"
                                    />
                                </div>
                                <div className="posting">
                                    <span className="userID point-span">{post.userName}</span>
                                    <div className="post-content"> {post.content} </div>
                                </div>
                            </div>
                            <div className="comment-section">
                                    {comments?.map((comment) => (
                                        <PostComment data={comment} key={comment.idx}></PostComment>
                                    ))}
                            </div>
                            </div>
                            <div className="post-info">
                                <div className="icons-react">
                                    <div className="icons-left">
                                        <img
                                            className="icon-react"
                                            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
                                            alt="heart_disLike" />
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
                                <div className='like'>{post.likeCnt} likes</div>
                                <div className='time-log'>{getTimeDifference(post)}</div>
                            </div>
                            <div className="comments-header">
                                <textarea
                                    className="comments-header-textarea"
                                    id="content"
                                    type="text"
                                    value={comment.content}
                                    onChange={handleInput}
                                    placeholder="Add a comment..."
                                ></textarea>
                                <button onClick={addComment}>Post</button>
                            </div>
                        </div>
                    </div>
                </section>
            ) : null}
        </div>
    );
};

export default PostModal;
