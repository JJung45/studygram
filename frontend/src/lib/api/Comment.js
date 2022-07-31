import axios from 'axios';
import client from './client';

const prefix = "/comment";

/**
 * 댓글을 전체 조회한다. (게시글 ID 기준)
 */
const getComments = (postId) => client.get(`${prefix}`, {params: 
    {postId: postId} 
});

/**
 * 댓글을 조회한다. (댓글 ID 기준)
 */
const getComment = ({commentId}) => client.get(`${prefix}/`, {commentId});

/**
 * 댓글을 저장한다.
 */
const addComment = ({comment}) => client.post(`${prefix}/save`, {comment});

/**
 * 댓글을 수정한다.
 */
const updateComment = ({comment}) => client.put(`${prefix}/update`, {comment});

/**
 * 댓글을 삭제한다.
 */
const deleteComment = ({commentId}) => client.delete(`${prefix}/delete/`, {commentId});


export default {getComments, getComment, addComment, updateComment, deleteComment};