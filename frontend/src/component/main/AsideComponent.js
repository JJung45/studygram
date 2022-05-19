import React from "react";
import styled from "styled-components";

const AsideComponent = () => {
  return (
    <div class="main-right">
      <div class="myProfile">
        <img
          class="pic"
          src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
          alt="minchoi 프로필 사진"
        />
        <div>
          <span class="userID point-span">minchoi</span>
          <span class="sub-span">Minkyeong Choi</span>
        </div>
      </div>
      <div class="section-recommend">
        <div class="menu-title">
          <span class="sub-title">회원님을 위한 추천</span>
          <span class="find-more">모두 보기</span>
        </div>
        <ul class="recommend-list">
          <li>
            <div class="recommend-friend-profile">
              <img
                class="img-profile"
                src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                alt="heaji님의 프로필 사진"
              />
              <div class="profile-text">
                <span class="userID point-span">heaji</span>
                <span class="sub-span">hakyeong님 외 2명이 팔로우합니다</span>
              </div>
            </div>
            <span class="btn-follow">팔로우</span>
          </li>
          <li>
            <div class="recommend-friend-profile">
              <img
                class="img-profile"
                src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                alt="_jeongjaehyun님의 프로필 사진"
              />
              <div class="profile-text">
                <span class="userID point-span">hakyeong</span>
                <span class="sub-span">heaji님이 팔로우합니다</span>
              </div>
            </div>
            <span class="btn-follow">팔로우</span>
          </li>
          <li>
            <div class="recommend-friend-profile">
              <img
                class="img-profile"
                src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                alt="leehi_hi님의 프로필 사진"
              />
              <div class="profile-text">
                <span class="userID point-span">test_test</span>
                <span class="sub-span">heaji님 외 5명이 팔로우합...</span>
              </div>
            </div>
            <span class="btn-follow">팔로우</span>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default AsideComponent;
