import React, { useCallback, useEffect, useState } from "react";
import axios from "axios";
// import moment from 'moment';
// import {Button, Dialog, DialogContent, IconButton, TextField} from "@mui/material";
import { useSelector } from "react-redux";
// import {jwtUtils} from "../../lib/jwtUtils";
// import api from "../utils/api";
import commentAPI from "../../lib/api/comment"
import { useLocation, useNavigate } from "react-router-dom";
// import DisabledByDefaultOutlinedIcon from "@mui/icons-material/DisabledByDefaultOutlined";
// import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
// import "../../styles/Comment.scss"
import styled from "styled-components";

const CommentComponent = () => {
  const tmp = process.env.REACT_APP_URL;
  // 로그인 후 현재 경로로 돌아오기 위해 useLocation 사용
  const location = useLocation();
  const navigate = useNavigate();
  const postId = location.state.data;
  const [commentList, setCommentList] = useState([]);
  // 새로운 댓글
  const [newComment, setNewComment] = useState({
    postId: postId,
    content: "",
  });
  // 선택된 댓글
  const [comment, setComment] = useState(0);
  // 현재 페이지, 전체 페이지 갯수
  const [page, setPage] = useState(0);
  const [pageCount, setPageCount] = useState(0);
  // modal이 보이는 여부 상태
  const [show, setShow] = useState(false);
  const Button = styled.button`
  border: none;
  background: #fff;
  border-radius: 15px;
  padding: 7px;
  width: 120px;
  font-weight: 600;
  color: #6b8eb3;
  cursor: pointer;
`;

  // 페이지에 해당하는 댓글 목록은 page 상태가 변경될 때마다 가져옴
  // 맨 처음 페이지가 1이므로 처음엔 1페이지에 해당하는 댓글을 가져온다
  useEffect(() => {
    commentAPI.getCommentList(postId)
        .then((result) =>{
          setCommentList(result.data);
        })

    /*
    const getCommentList = async () => {
      const params = {
        limit: page,
        offset: 0,
      };
      const { data } = await commentAPI.getComments(postId, params);
      console.log("commentList:" + data);
      return data;
    };
    // 기존 commentList에 데이터를 덧붙임
    getCommentList().then((result) =>
      setCommentList([...commentList, ...result])
    );

     */
  }, [page]);

  // 페이지 카운트는 컴포넌트가 마운트되고 딱 한번만 가져오면됨
  useEffect(() => {
    // 댓글 전체 갯수 구하기
    commentAPI.getCommentCnt(Number(postId))
        .then((result) => {
          const totalCnt = result.data;
          // setPageCount(Math.ceil(totalCnt / 5));
          setPageCount(Math.ceil(totalCnt / 1));
          console.log("page:", page, "pageCount:", pageCount, "Math:", Math.ceil(totalCnt / 5));
        })
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;

    setNewComment((preNewComment) => ({
      ...preNewComment,
      [name]: value,
    }));
  };

  // CASE 1.
  const handleSubmit = (e) => {
    e.preventDefault();
    if(newComment.content === '')
    {
      alert('내용을 입력하세요');
      return;
    }
    addComment(newComment).then(r => console.log());
  };

  // CASE 2.
  // const handleSubmit = (e) => {
  //   e.preventDefault();
  //   if(newComment.content == '') {
  //     alert("댓글을 입력해주세요");
  //     return;
  //   }
  //   console.log("state" + newComment);
  //   axios.post(`/comment/save/`, newComment)
  //   .then((res) => {
  //     newComment.commentId = res.data;
  //     setNewComment(newComment);
  //     const getCommentList = async () => {
  //       const params = {
  //         postId: postId,
  //         limit: page,
  //         offset: 0,
  //       };
  //       const { data } = await commentAPI.getComments(params);
  //       console.log("commentList:" + data);
  //       return data;
  //     };
  //     // 기존 commentList에 데이터를 덧붙임
  //     getCommentList().then((result) =>
  //       setCommentList([...commentList, ...result])
  //     );

  //     // input 공백 만들기
  //     setNewComment({
  //       ...newComment,
  //       content: "",
  //     });
  //   });
  // };
  // 댓글 추가하기, 댓글 추가하는 API는 인증 미들웨어가 설정되어 있으므로
  // HTTP HEADER에 jwt-token 정보를 보내는 interceptor 사용
  // const submit = useCallback(async () => {
  // const comment = {
  //     postId: postId,
  //     // DB에 엔터가 먹힌 상태로 들어가므로 제대로 화면에 띄우기 위해 <br>로 치환
  //     content: content,
  //   //   user_id: jwtUtils.getId(token)
  //     userId: 12
  //   }
  //   // axios interceptor 사용 : 로그인한 사용자만 쓸 수 있다!
  //   console.log("new comment:" + comment);
  //   addComment(comment);
  //   alert("댓글 등록 완료");
  //   window.location.reload();
  // }, [content]);

  const handleDelete = (commentId) => {
    console.log("삭제될 comment:" + commentId);
    const comments = commentList.filter((item) => item.idx !== commentId);
    setCommentList(comments);
    deleteComment(commentId);
  };

  /*modal 관련 코드*/
  // 로그인 후 돌아올 수 있게 현재 경로 세팅
  const goLogin = () => {
    setShow(false);
    navigate(`/login?redirectUrl=${location.pathname}`);
  };
  // 로그인을 하지 않은 상태에서 댓글 입력 창을 클릭하면 Modal이 열림.
  //   const isLogin = () => {
  //     if (!jwtUtils.isAuth(token)) {
  //       setShow(true);
  //     }
  //   }

  const addComment = async(comment) => {
    // Client 에 Token 담긴 axios 사용
    await commentAPI.addComment({
      postId: postId,
      content: newComment.content
    })
    .then(() => {
      commentAPI.getCommentList(postId)
          .then((result) => setCommentList(result.data));
      /*
      const getCommentList = async () => {
        const params = {
          postId: postId,
          limit: page,
          offset: 0,
        };
        const { data } = await commentAPI.getComments(postId, params);
        console.log("commentList:" + data);
        return data;
      };
       // 기존 댓글목록에 새로 등록한 댓글 붙이기
      getCommentList().then((result) => setCommentList(result));
      */
      setNewComment({
        ...newComment,
        content: "",
      });
    })
    .catch((err) => {
      console.log("Add New Comment Failed!", err);
    });


    /*
    axios.post(`${tmp}/comment/save/`, comment)
      .then(() => {
        const getCommentList = async () => {
          const params = {
            postId: postId,
            limit: page,
            offset: 0,
          };
          const { data } = await commentAPI.getComments(params);
          console.log("commentList:" + data);
          return data;
        };
         // 기존 commentList에 데이터를 덧붙임
        getCommentList().then((result) => setCommentList(result));

        setNewComment({
          ...newComment,
          content: "",
        });
      })
      .catch((err) => {
        console.log("Add Comment() Error!", err);
      });
    */
  };

  const deleteComment = (commentId) => {
    commentAPI.deleteComment(commentId)
        .then(() => {
          console.log('Delete Comment(ID:'+commentId+')');
        })
        .catch((err) => {
          console.log('Delete Comment Failed!', err);
        })
  };

  // const getComment = useCallback(() => {
  //   let params = {
  //     count: 5,
  //   };
  //   if (nextId) {
  //     params.next_id = nextId;
  //   }
  //   dispatch(getTimelineAction(params));
  // }, [dispatch, nextId]);

  // const onClickMoreButton = useCallback(() => {
  //   if (!nextId) {
  //     alert("모든 데이터를 조회했습니다.");
  //   } else {
  //     getComment();
  //   }
  // }, [nextId, getComment]);

  const onClickMoreButton = useCallback(() => {
     setPage(page + 1);
  });


  return (
    <div className="comments-wrapper">
      <div className="comments-header">
        {/* <input
          className="comments-header-textarea"
          maxRows={3}
          onClick={isLogin}
          onChange={(e) => {
            setContent(e.target.value)
          }}
          multiline placeholder="댓글을 입력해주세요✏️"
        /> */}
        {/* {content !== "" ? (
          <button variant="outlined" onClick={submit}>등록하기</button>
        ) : (
          <button variant="outlined" disabled={true}>
            등록하기
          </button>
        )} */}
      </div>
      <div className="comments-body">
        {commentList.map((item, index) => (
          <div key={index} className="comments-comment">
            <div className="comment-username-date">
              <div className="comment-date">{item.createdDate}</div>
            </div>
            <div>{item.idx}</div>
            <div className="comment-username">{item.username}</div>
            <div
              className="comment-content"
              // onClick={() => changeContent(item.idx, item.content)}
              onClick={() => setNewComment({
                commentId: item.idx,
                content: item.content})}
              >
              {item.content}
            </div>
            {/*삭제버튼 내 아이디 인것만 보이도록 하는것 필요*/}
            <button className="delete" onClick={() => handleDelete(item.idx)}>
              삭제
            </button>
            <hr />
          </div>
        ))}
      </div>
      {
        /*
          page(현재 페이지)와 pageCount(총 페이지 갯수)가 같으면 서버에서
          모든 댓글을 가져온 상태이므로 댓글 더보기 버튼이 보이지 않게 한다.
          page의 초기 상태가 1이기 때문에 컴포넌트가 마운트 된 후 첫페이지를 가져오고 만약 pageCount가 5이고
          현재 page가 4라면 버튼을 누르는 순간 page가 5가되어 마지막 페이지의 데이터를 가져온다.
        */
        page < pageCount && (
          <div
            className="button_box"
            style={{ textAlign: "center", margin: "20px" }}
          >
            <Button onClick={onClickMoreButton}> + read more...</Button>
          </div>
        )
      }

    <div>
      {/* <TextField */}
      <form onSubmit={handleSubmit} method="post">
        <input id="postId" name="postId" value={newComment.postId || ""} hidden />
        <input
          className="comments-header-textarea"
          id="content"
          name="content"
          placeholder="댓글을 입력하세요"
          value={newComment.content || ''}
          onChange={handleChange}
        />
        <button type="submit">입력</button>
      </form>
      </div>

      {/*modal*/}
      {/* <Dialog open={show}>
        <DialogContent style={{position: "relative"}}>
          <IconButton
            style={{position: "absolute", top: "0", right: "0"}}
            onClick={() => {
              setShow(false)
            }}
          >
            <DisabledByDefaultOutlinedIcon/>
          </IconButton>
          <div className="modal">
            <div className="modal-title">로그인이 필요합니다</div>
            <div className="modal-content">로그인 페이지로 이동하시겠습니까?</div>
            <div className="modal-button">
              <Button
                variant="outlined" color="error"
                onClick={goLogin}
              >
                예
              </Button>
              <Button
                variant="outlined" color="primary"
                onClick={() => {
                  setShow(false)
                }}
              >
                아니오
              </Button>
            </div>
          </div>
        </DialogContent>
      </Dialog> */}
    </div>
  );
};
export default CommentComponent;
