import axios from "axios";

const client = axios.create({
  baseURL: process.env.REACT_APP_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Set the AUTH token for any request
client.interceptors.request.use(function (config) {
  const token = localStorage.getItem('jwtToken');
  config.headers.Authorization =  token ? `Bearer ${token}` : '';
  return config;
});

export default client;