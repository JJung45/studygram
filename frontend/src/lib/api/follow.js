import client from './client';

/**
 * 팔로잉 / 팔로워 조회 => User 조회 시, 함께 받아온다.
 */

const follow = (follow) => client({
    method: 'post',
    url: `/follow`,
    data: follow,
});

const unfollow = (follow) => client({
    method: 'post',
    url: `/unfollow`,
    data: follow,
});
export default {follow, unfollow};
