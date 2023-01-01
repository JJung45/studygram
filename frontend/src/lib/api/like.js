import client from "./client";

const prefix = "/api/like";
/**
 * 좋아요 저장한다.
 */
const save = (like) =>
  client({
    method: "post",
    url: `${prefix}/save`,
    data: like,
  });

/**
 * 좋아요 취소한다.
 */
const cancle = (postId) =>
  client({
    method: "delete",
    url: `${prefix}/${postId}`,
  });

/**
 * 좋아요 누른 회원들을 불러온다.
 */
const users = (postId) =>
  client({
    method: "get",
    url: `${prefix}/${postId}`,
  });

export default { save, cancle, users };
