import React from "react";
import styled from "styled-components";

const NavComponent = ({ form, onChange, onSubmit }) => {
  return (
    <nav>
      <div class="nav-container">
        <div class="nav-1">
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Instagram_logo.svg/320px-Instagram_logo.svg.png"
            alt="logo_img"
          />
        </div>
        <input
          id="searchInput"
          type="search"
          class="input-search"
          placeholder="검색"
        />
        <div class="nav-2">
          <img
            src="https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png"
            alt="home"
          />
          <img
            src="https://cdn3.iconfinder.com/data/icons/email-133/32/Email_paper_air_plane_airplane_send_message-512.png"
            alt="direct-message"
          />
          <img
            src="https://cdn2.iconfinder.com/data/icons/font-awesome/1792/plus-square-o-512.png"
            alt="plus"
          />
          <img
            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/explore.png"
            alt="search"
          />
          <img
            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
            alt="like"
          />
          <img
            class="pic"
            src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
            alt="mypage"
          />
        </div>
      </div>
    </nav>
  );
};

export default NavComponent;
