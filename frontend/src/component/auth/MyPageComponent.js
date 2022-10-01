import React from "react";
import axios from "axios";
import "../../styles/Auth.css";
import styled from "styled-components";
import queryString from 'query-string';
import { useLocation } from "react-router-dom";
import setAuthorizationToken from "../../lib/api/setAuthorizationToken";
import { AxiosContext } from "react-axios/lib/components/AxiosProvider";


const InputBox = styled.div`
  margin: auto 40px 6px;
`;
const Input = styled.input`
  height: 36px;
  border: 1px solid #efefef;
  border-radius: 3px;
  background-color: #fafafa;
  width: 100%;
  font-size: 12px;
  margin: 0;
  padding: 9px 0 7px 8px;
  outline: none;
  overflow: hidden;
  text-overflow: ellipsis;
  box-sizing: border-box;

  #name:focus,
  #password:focus {
    border-color: #bbb;
  }
`;

const MyPageComponent = ({}) => {
  const search = useLocation().search;
  const token = new URLSearchParams(search).get("token");
  console.log(token);
  if(token) {
    const userObj = { token: token };
    // window.localStorage.setItem('jwtToken', JSON.stringify(userObj));
    // setAuthorizationToken(token);
    window.localStorage.setItem('jwtToken', token);
    console.log("localStorage: "+localStorage.getItem('jwtToken'));
  }

  return (
    <div className="article">
      <div className="content">
        <h1>로그인 완료</h1>
      </div>
      <div>
        <a href="/logout">로그아웃</a>
      </div>
    </div>
  );
};;

export default MyPageComponent;