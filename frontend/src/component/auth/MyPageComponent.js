import React, {useState, useEffect, useRef} from "react";
import {Link, useLocation} from "react-router-dom";

import BoardComponent from "./BoardComponent";
import SavedComponent from "./SavedComponent";
import TagComponent from "./TagComponent";

import UserApi from "../../lib/api/user";
import ProfileModal from "../ProfileModal";

const MyPageComponent = ({}) => {
  const statusType = {
    BOARD: "board",
    SAVED: "saved",
    TAG: "tag",
  };
  const pathname = useLocation().pathname;
  const [type, setType] = useState(statusType.BOARD);
  const [imgUrl, setImgUrl] = useState(null);
  const [user, setUser] = useState(async () => {
    await UserApi.userInfo(pathname.split("/")[1]).then((res) => {
      setUser(res.data.body.user);
      setImgUrl(res.data.body.user.profileImageUrl);
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

  const fileInput = useRef(null);
  const saveProfileImage = (e) => {
    console.log('프로필 이미지 선택');
    if(e.target.files[0]) {
      console.log('file', e.target.files[0]);
      setImgUrl(e.target.files[0])
    } else {
      setImgUrl(user.profileImageUrl);
      return;
    }
    const reader = new FileReader();
    reader.onload = () => {
      if(reader.readyState === 2) {
        setImgUrl(reader.result);
      }
    }
    reader.readAsDataURL(e.target.files[0]);

    // file 보낼때 무조건 form ㅡㅇ로 보내야하나? multipart-form 이라서?
    let formData = new FormData();
    formData.append("fileImage", e.target.files[0])

    UserApi.userProfileImageUpload(user.idx, formData)
        .then((res)=>{
          console.log("Res", res);
        })




  }

  const modal = {
    INFO: "info",
    // EDIT: "edit",
    SETTING: "setting",
  };
  const [modalType, setModalType] = useState(modal.INFO);
  // 소개 모달
  // 프로필편집 버튼 모달
  // 설정 모달
  const [profileModalOpen, setProfileModalOpen] = useState(false);
  const openModal = (mType) => {
    setProfileModalOpen(true);
    setModalType(mType);
  };
  const closeModal = () => {
    setProfileModalOpen(false);
  };

  return (
    <div>
      <div className="container">
        <div className="profile">
          <div className="profile-image">
            <img src={imgUrl} alt="" onClick={() => fileInput.current.click()}/>
            <input name="profileImage" type="file" accept="image/*" id="profileImage" onChange={saveProfileImage} style={{display:"none"}} ref={fileInput}/>
          </div>

          <div className="›profile-user-settings">
            <h1 className="profile-user-name" onClick={()=>openModal(modal.INFO)}>{user.userName}</h1>
            <button className="profile-edit-btn">
              <Link to={"/accounts/edit"} style={{
                textDecoration: "none",
                color: "black"}}>프로필 편집</Link>
            </button>
            <button aria-label="profile settings" onClick={()=>openModal(modal.SETTING)}>
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
      <ProfileModal open={profileModalOpen} user={user} type={modalType} close={closeModal}></ProfileModal>


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
