import React from "react";
import "../../styles/Auth.css";
import styled from "styled-components";


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

const MyPageComponent = ({ }) => {
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
};

export default MyPageComponent;