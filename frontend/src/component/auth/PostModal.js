import React, {useState, useEffect, useRef} from "react";
import PostComment from "../main/PostCommentComponent";
import "../../styles/modal.css";
import "../../styles/Write.css";
import commentAPI from "../../lib/api/comment";
import moment from "moment";

const PostModal = (props) => {
    const {open, close, post} = props;
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
            })
        }
    }, [post]);

    // 모달 영역 밖 클릭 시 닫기
    const node = useRef(null);
    const modalCloseHandler = (e) => {
        if(open && !node.current.contains(e.target)) {
            console.log('닫혀야지');
            close();
        }
    }

    useEffect(() => {
        if (open) {
            window.addEventListener('click', modalCloseHandler);
        } else {
            window.removeEventListener('click', modalCloseHandler);
        }

        return () => {
            window.removeEventListener('click', modalCloseHandler);
        };
    }, [open]);


    const handleInput = (e) => {
        setComment({
            ...comment,
            content: e.target.value,
        });
    };

    const addComment = async () => {
        await commentAPI.addComment(comment)
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
        })
    };

    return (
        <div className={open ? "openModal modal" : "modal"}>
            {open ? (
               <div className={open ? "openModal modal" : "modal"}>
               {open ? (
                 <section>
                   <button className="close" onClick={close}>
                     &times;
                   </button>
                   <div className="postModal">
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
                       <hr />
                       <div style={{height: "20%"}}>
                         <span className="userId point-span">{post.userName}</span>
                         &nbsp; {post.content}
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
                         <div className="time-log">
                           <span>{moment(post.createdDate).format("YYYY-MM-DD")}</span>
                         </div>
                     </div>
                   </div>
                 </section>
               ) : null}
             </div>
            ) : null}
        </div>
    );
};

export default PostModal;
