import client from './client';

/**
 * 팔로잉 / 팔로워 조회 => User 조회 시, 함께 받아온다.
 */

const follow = (follow) => client({
    method: 'post',
    url: `/follow`,
    data: follow,
});

const unfollow = (userIdx) => client({
    method: 'delete',
    url: `/unfollow?toUserIdx=${userIdx}`,
});

const chkFollow = (userIdx) => client({
    method: 'get',
    url: `/followChk?toUserIdx=${userIdx}`,
});

const getSuggestions = (userIdx) => client({
    method: 'get',
    url: `/suggestions?userIdx=${userIdx}`,
})
export default {follow, unfollow, chkFollow, getSuggestions};
