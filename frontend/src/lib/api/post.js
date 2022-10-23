import client from "./client";

const prefix = "/post";

/**
 * 게시물을 저장한다.
 */
const addPost = (post) => client({
  method: 'post',
  url: `${prefix}/save`,
  data: post,
});


/**
 * 게시물을 수정한다.
 */
const updatePost = ({ post, postId }) =>
  client.put(`${prefix}`, { post, postId });

/**
 * 게시물을 삭제한다.
 */
const deletePost = ({ postId }) => client.delete(`${prefix}`, { postId });

/**
 * 게시물을 조회한다.
 */
const getPost = ({ postId }) => client.get(`${prefix}`, { postId });

/**
 * 게시물을 전체 조회한다.
 */
const getPosts = () => client.get(`${prefix}/`);

export default { addPost, updatePost, deletePost, getPost, getPosts };
