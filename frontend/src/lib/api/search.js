import client from './client'

const prefix = "/search";

const search = (keyword, type) => client({
    method: 'get',
    url: `${prefix}/v1?keyword=${keyword}&type=${type}`,
});

const searchBoth = (keyword) => client({
    method: 'get',
    url: `${prefix}/v2?keyword=${keyword}`,
});

const searchPosts = (tagIdx, ordering) => client({
    method: 'get',
    url: `${prefix}/post?tagIdx=${tagIdx}&ordering=${ordering}`,
});
export default {search, searchBoth, searchPosts};