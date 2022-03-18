import React from "react";
import styled from "styled-components";
import { Link } from 'react-router-dom';
import logo from './../../assets/lock-icon.png';


const InputBox = styled.div`
  margin: auto 40px 12px;
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

const ForgetPasswordComponent = () => {
    return (
        <div className="article">
          <div className="content forget-content">
            <div className="login-box">
              <div className="header forgot-header">
                <img
                  className="logo"
                  src={logo}
                  alt="Instagram-forgot"
                />
                <h4>Trouble Logging In?</h4>
                <div className="forget-text">
                    Enter your email, phone, or username and we'll send you a link to get back into your account.
                </div>
              </div>
              <div className="form-wrap">
                <InputBox>
                    <Input
                      name="username"
                      type="text"
                      placeholder="Phone number, username, or email"
                      autocorrect="off"
                      autocapitalize="off"
                      maxlength="30"
                      onChange=""
                      value=""
                      required
                    />
                </InputBox>
                <span className="button-box">
                    <button className="btn" type="submit" name="submit">
                        Send Login Link
                    </button>
                </span>
              </div>

              <div className="text">
                  <Link to="/register">Create New Account</Link>
              </div>
            </div>
          </div>
        </div>
      );
};

export default ForgetPasswordComponent;