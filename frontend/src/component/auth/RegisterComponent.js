import React from "react";
import "../../styles/Auth.css";
import styled from "styled-components";
import { Route, Link, Routes } from 'react-router-dom';
import LoginPage from "../../pages/LoginPage";


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
            <form className="form">
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
                      required
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
                      required
                    />
                  </InputBox>
              <span className="button-box">
                <button className="btn" type="submit" name="submit" onSubmit={onSubmit}>
                  Log in
                </button>
              </span>
              <a className="forgot" href="#">
                Forgot password?
              </a>
            </form>
          </div>
        </div>

        <div className="login-box">
          <div className="text">
              Do you have account?<Link to="/login">Sign up</Link>
            <Routes>
                <Route path="/login" element={props => <LoginPage {...props}/>} />
            </Routes>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterComponent;