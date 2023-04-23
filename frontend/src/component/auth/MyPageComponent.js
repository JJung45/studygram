import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import BoardComponent from "./BoardComponent";
import SavedComponent from "./SavedComponent";
import TagComponent from "./TagComponent";

import UserApi from "../../lib/api/user";

const MyPageComponent = ({}) => {
  const statusType = {
    BOARD: "board",
    SAVED: "saved",
    TAG: "tag",
  };
  const pathname = useLocation().pathname;
  const [type, setType] = useState(statusType.BOARD);
  const [user, setUser] = useState(async () => {
    await UserApi.userInfo(pathname.split("/")[1]).then((res) => {
      setUser(res.data.body.user);
    });
  });

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
      <div className="container">
        <div className="profile">
          <div className="profile-image">
            <img src={user.profileImageUrl} alt="" />
          </div>

          <div className="profile-user-settings">
            <h1 className="profile-user-name">{user.userName}</h1>
            <button className="profile-edit-btn">프로필 편집</button>
            <button aria-label="profile settings">
              <img
                className="profile-settings-btn"
                src="https://cdn4.iconfinder.com/data/icons/glyphs/24/icons_settings-512.png"
              />
            </button>
          </div>

          <div className="profile-stats">
            <ul>
              <li>
                <span className="profile-stat-count">{user.postCnt}</span> posts
              </li>
              <li>
                <span className="profile-stat-count">{user.followersCnt}</span>{" "}
                followers
              </li>
              <li>
                <span className="profile-stat-count">{user.followingCnt}</span>{" "}
                following
              </li>
            </ul>
          </div>

          <div className="profile-bio">
            <p>
              <span className="profile-real-name">{user.fullName}</span>
              <br />
              {user.profileMsg}
            </p>
          </div>
        </div>
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
            <img
              className="icon-react"
              src="https://cdn1.iconfinder.com/data/icons/bootstrap-vol-3/16/grid-3x3-512.png"
            ></img>
            <span>게시물</span>
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
