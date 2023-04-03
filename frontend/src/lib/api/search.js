import client from './client'

const prefix = "/search";

const search = (keyword, type) => client({
    method: 'get',
    url: `${prefix}?keyword=${keyword}&type=${type}`,
});
export default {search};