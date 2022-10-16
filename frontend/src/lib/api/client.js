import axios from "axios";

const client = axios.create({
  Authorization : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY1MDU0NjM3Nn0.yNzBiddD8ZC44_NnIwckB1eHDDDu9FKIoJKStNkyR70",
  baseURL: process.env.REACT_APP_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Set the AUTH token for any request
client.interceptors.request.use(function (config) {
  const token = localStorage.getItem('jwtToken');
  console.log('jwt::'+token);
  config.headers.Authorization =  token ? `Bearer ${token}` : '';
  console.log('config' , config);
  return config;
});

export default client;