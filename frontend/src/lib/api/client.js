import axios from "axios";
import { useNavigate } from "react-router-dom";

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

client.interceptors.response.use(function (response) {
  // 2xx 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
      // 응답 데이터가 있는 작업 수행
      console.log("sfadf");
      return response;
    }, function (error) {
      // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
      // 응답 오류가 있는 작업 수행
      // TODO error.response의 메세지를 못가져오 ㅁ체크 필요
      console.log("asdf"+error.response.data);
      window.alert(error.response);
    });


export default client;
