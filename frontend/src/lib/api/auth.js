import client from './client';

// login
export const login = ({ username, password }) =>
  client.post('/api/auth/login', { username, password });

// register
export const register = ({ username, password, fullname }) =>
  client.post('/api/auth/register', { username, password, fullname });

const getUserName = () => client.get('/users/name');
const getUserInfo = () => client.get('/usrs/info');
export default {getUserName, getUserInfo}