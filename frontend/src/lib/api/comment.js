import client from "./client";

const prefix = "/comment";

/**
 * 댓글을 전체 조회한다. (게시글 ID 기준)
 */
const getComments = (postIdx, params) =>
  client({
    method: "get",
    url: `${prefix}?postIdx=${postIdx}`,
    data: params,
  });

const getCommentList = (postIdx) =>
  client({
    method: "get",
    url: `${prefix}/list?postIdx=${postIdx}`,
  });

/**
 * 댓글을 조회한다. (댓글 ID 기준)
 */
const getComment = ({ commentId }) => client.get(`${prefix}/`, { commentId });

/**
 * 댓글 갯수를 조회한다. (게시글 ID 기준)
 */
// const getCommentCnt = ({postId}) => client.get(`${prefix}/count/`, {postId});
const getCommentCnt = (postIdx) =>
  client({
    method: "get",
    url: `${prefix}/count/${postIdx}`,
  });

/**
 * 댓글을 저장한다.
 */
// const addComment = ({comment}) => client.post(`${prefix}/save`, JSON.stringify(comment));
const addComment = (comment) =>
  client({
    method: "post",
    url: `${prefix}/save`,
    data: comment,
  });

/**
 * 댓글을 수정한다.
 */
const updateComment = ({ comment }) =>
  client({
    method: "put",
    url: `${prefix}/update`,
    data: comment,
  });

/**
 * 댓글을 삭제한다.
 */
const deleteComment = ({ commentIdx }) =>
  client({
    method: "delete",
    url: `${prefix}/delete/${commentIdx}`,
  });

export default {
  getComments,
  getCommentList,
  getComment,
  getCommentCnt,
  addComment,
  updateComment,
  deleteComment,
};
