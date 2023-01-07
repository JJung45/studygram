import React, { useState, useEffect } from "react";

import BoardComponent from "./BoardComponent";
import SavedComponent from "./SavedComponent";
import TagComponent from "./TagComponent";

const MyPageComponent = ({}) => {
  const statusType = {
    BOARD: "board",
    SAVED: "saved",
    TAG: "tag",
  };
  const [type, setType] = useState(statusType.BOARD);
  const setComponentByType = () => {
    if (type === statusType.BOARD) {
      return <BoardComponent></BoardComponent>;
    } else if (type === statusType.SAVED) {
      return <SavedComponent></SavedComponent>;
    } else if (type === statusType.TAG) {
      return <TagComponent></TagComponent>;
    } else {
      return <BoardComponent></BoardComponent>;
    }
  };
  return (
    <div>
      <div className="myPage">
        <div className="left-box">
          <img
            className="pic"
            src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
            alt="minchoi 프로필 사진"
          />
        </div>
        <section>
          <div className="profile-box">
            <p>
              <span className="id">minguinsta</span>
              <button className="edit-btn">프로필 편집</button>
            </p>
          </div>
          <p className="numbers">
            <span className="title">게시물</span>
            <span className="value">36</span>
            <span className="title">팔로워</span>
            <span className="value">154</span>
            <span className="title">팔로우</span>
            <span className="value">156</span>
          </p>
          <div className="intro">
            <p>
              민경
              <br />
              <span>min</span>
            </p>
          </div>
        </section>
      </div>
      <div className="story">
        <div className="story-box">
          <img
            className="pic"
            src="https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"
            alt="pre-story"
          ></img>
          <p>멍</p>
        </div>
        <div className="story-box">
          <img
            className="pic"
            src="https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"
            alt="pre-story"
          ></img>
          <p>멍</p>
        </div>
        <div className="story-box">
          <img
            className="pic"
            src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-ios7-plus-empty-512.png"
            alt="pre-story"
          ></img>
          <p>신규</p>
        </div>
      </div>
      <div className="feed">
        <div className="feed-header">
          <div
            className="sub"
            onClick={(event) => {
              setType(statusType.BOARD);
            }}
          >
            <span>
              <img
                className="icon-react"
                src="https://cdn1.iconfinder.com/data/icons/bootstrap-vol-3/16/grid-3x3-512.png"
              ></img>
              <span>게시판</span>
            </span>
          </div>
          <div
            className="sub"
            onClick={(event) => {
              setType(statusType.SAVED);
            }}
          >
            <span>
              <img
                className="icon-react"
                src="https://cdn0.iconfinder.com/data/icons/ui-interface-6/24/bookmark-512.png"
              ></img>
              <span>저장됨</span>
            </span>
          </div>
          <div
            className="sub"
            onClick={(event) => {
              setType(statusType.TAG);
            }}
          >
            <span>
              <img
                className="icon-react"
                src="https://cdn0.iconfinder.com/data/icons/phosphor-light-vol-5/256/user-square-light-512.png"
              ></img>
              <span>태그됨</span>
            </span>
          </div>
        </div>
        <div className="main-box">
          <div>{setComponentByType()}</div>
        </div>
      </div>
    </div>
  );
};

export default MyPageComponent;
