import React from "react";
import "../../styles/Auth.css";
import styled from "styled-components";
import { Link } from 'react-router-dom';


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

const RegisterComponent = ({ form, onChange, onSubmit }) => {
  return (
    <div className="article">
      <div className="content">
        <div className="login-box">
          <div className="header">
            <img
              className="logo"
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Instagram_logo.svg/1200px-Instagram_logo.svg.png"
              alt="Instagram"
            />
          </div>
          <div className="form-wrap">
            <a className="button-box" href="http://localhost:8090/oauth2/authorization/google?redirect_uri=http://localhost:3000/oauth2/redirect">
              <button className="btn">
                Log in with Google
              </button>
            </a>
            <a className="button-box" href="http://localhost:8090/oauth2/authorization/naver?redirect_uri=http://localhost:3000/oauth2/redirect">
              <button className="btn">
                Log in with Naver
              </button>
            </a>
            <a className="button-box" href="http://localhost:8090/oauth2/authorization/kakao?redirect_uri=http://localhost:3000/oauth2/redirect">
              <button className="btn">
                Log in with KaKao
              </button>
            </a>
            <form className="form">
                  
                  <div className="or-line">
                    <span className="line"></span> <span className="or">OR</span> <span className="line"></span>
                  </div>
                  <InputBox>
                    <Input
                      name="username"
                      type="text"
                      placeholder="Phone number, username, or email"
                      autocorrect="off"
                      autocapitalize="off"
                      maxlength="30"
                      onChange={onChange}
                      value={form.username}
                      // required
                    />
                  </InputBox>
                  <InputBox>
                    <Input
                      name="fullname"
                      type="text"
                      placeholder="fullname"
                      autocorrect="off"
                      autocapitalize="off"
                      onChange={onChange}
                      value={form.fullname}
                      maxlength="30"
                    />
                  </InputBox>
                  <InputBox>
                    <Input
                      name="password"
                      type="password"
                      placeholder="password"
                      autocorrect="off"
                      autocapitalize="off"
                      onChange={onChange}
                      value={form.password}
                      maxlength="30"
                      // required
                    />
                  </InputBox>
              <span className="button-box">
                <button className="btn" type="submit" name="submit" onSubmit={onSubmit}>
                  Log in
                </button>
              </span>
            </form>
          </div>
        </div>

        <div className="login-box">
          <div className="text">
              Do you have account?<Link to="/">Login</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterComponent;