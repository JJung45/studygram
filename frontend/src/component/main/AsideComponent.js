import React from "react";
import styled from "styled-components";

const AsideComponent = () => {
  return (
    <div className="main-right">
      <div className="myProfile">
        <img
          className="pic"
          src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
          alt="minchoi 프로필 사진"
        />
        <div>
          <span className="userID point-span">minchoi</span>
          <span className="sub-span">Minkyeong Choi</span>
        </div>
      </div>
      <div className="section-recommend">
        <div className="menu-title">
          <span className="sub-title">회원님을 위한 추천</span>
          <span className="find-more">모두 보기</span>
        </div>
        <ul className="recommend-list">
          <li>
            <div className="recommend-friend-profile">
              <img
                className="img-profile"
                src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                alt="heaji님의 프로필 사진"
              />
              <div className="profile-text">
                <span className="userID point-span">heaji</span>
                <span className="sub-span">
                  hakyeong님 외 2명이 팔로우합니다
                </span>
              </div>
            </div>
            <span className="btn-follow">팔로우</span>
          </li>
          <li>
            <div className="recommend-friend-profile">
              <img
                className="img-profile"
                src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                alt="_jeongjaehyun님의 프로필 사진"
              />
              <div className="profile-text">
                <span className="userID point-span">hakyeong</span>
                <span className="sub-span">heaji님이 팔로우합니다</span>
              </div>
            </div>
            <span className="btn-follow">팔로우</span>
          </li>
          <li>
            <div className="recommend-friend-profile">
              <img
                className="img-profile"
                src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                alt="leehi_hi님의 프로필 사진"
              />
              <div className="profile-text">
                <span className="userID point-span">test_test</span>
                <span className="sub-span">heaji님 외 5명이 팔로우합...</span>
              </div>
            </div>
            <span className="btn-follow">팔로우</span>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default AsideComponent;
