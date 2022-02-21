import React from "react";
import "../../../styles/Register.css";
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

// type = register or login
const Auth = ({ type, onChange, onSubmit }) => {
  return (
    <div className="article">
      <div className="content">
        <div class="login-box">
          <div class="header">
            <img
              class="logo"
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Instagram_logo.svg/1200px-Instagram_logo.svg.png"
              alt="Instagram"
            />
          </div>
          <div class="form-wrap">
            <form id="register" class="form">
              {type === "register" ? (
                <>
                  <InputBox>
                    <Input
                      name="username"
                      type="text"
                      placeholder="Phone number, username, or email"
                      autocorrect="off"
                      autocapitalize="off"
                      maxlength="30"
                      onChange={onChange}
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
                      maxlength="30"
                      required
                    />
                  </InputBox>
                </>
              ) : (
                <>
                  <InputBox>
                    <Input
                      name="username"
                      type="text"
                      placeholder="Phone number, username, or email"
                      autocorrect="off"
                      autocapitalize="off"
                      onChange={onChange}
                      maxlength="30"
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
                      maxlength="30"
                    />
                  </InputBox>
                  <InputBox></InputBox>
                </>
              )}

              <span class="button-box">
                <button class="btn" type="submit" name="submit" onSubmit={onSubmit}>
                  Log in
                </button>
              </span>
              <a class="forgot" href="">
                Forgot password?
              </a>
            </form>
          </div>
        </div>

        <div class="login-box">
          <p class="text">
            Don't have an account?<a href="#">Sign up</a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Auth;
