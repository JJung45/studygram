import React, { useEffect, useState, useRef } from "react";
import SavePostModal from "./SavePostModal";
import SearchBoxComponent from "./SearchBoxComponent";
import PostModal from "../auth/PostModal";
import UserApi from "../../lib/api/user";
import NotificationApi from "../../lib/api/notification";
import PostApi from "../../lib/api/post";
import "../../styles/alarm.css";
import { useLocation, useNavigate } from "react-router-dom";

const NavComponent = () => {
  // useState를 사용하여 open상태를 변경한다. (open일때 true로 만들어 열리는 방식)
  const [modalOpen, setModalOpen] = useState(false);
  const [postModalOpen, setPostModalOpen] = useState(false);
  const [selectedPost, setSelectedPost] = useState(null);

  const [notificationState, setNotificationState] = useState(false);
  const [notificationList, setNotificationList] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  const openModal = () => {
    document.body.style = `overflow: hidden`;
    setModalOpen(true);
  };
  const closeModal = () => {
    document.body.style = `overflow: visible`;
    setModalOpen(false);
  };

  const openPostModal = async (post) => {
    setSelectedPost(post);
    document.body.style = `overflow: hidden`;
    setPostModalOpen(true);
  };

  const closePostModal = () => {
    setPostModalOpen(false);
    document.body.style = `overflow: visible`;
    setSelectedPost(null);
  };

  const getNotificationState = async () => {
    await NotificationApi.getNotReadNotificationsCount().then((res) => {
      if (res.data >= 1) {
        setNotificationState(true);
      }
    });
  };

  const getNotifications = async () => {
    if (notificationState) {
      await NotificationApi.getNotifications().then((res) => {
        setNotificationList(res.data);
        setNotificationState(false);
      });
    } else {
      setNotificationList(null);
      setNotificationState(true);
    }
  };

  const convertNotificationMessage = (notification) => {
    const userNameRegex = new RegExp(notification.fromUser.userName, "g");

    return notification.message.replace(
      userNameRegex,
      `<a href="/${notification.fromUser.userName}">${notification.fromUser.userName}</a>`
    );
  };

  const getNotificationMessage = (notification) => {
    let message = "";

    if (notification.notificationType == "덧글") {
      message = notification.comment.content;
    }

    return message;
  };

  const clickNotificationMessage = async (post) => {
    await PostApi.getPost(post.idx).then((result) => {
      openPostModal(result.data);
    });
  };

  useEffect(() => {
    getNotificationState();
  }, []);

  const styles = {
    contentDiv: {
      display: "flex",
    },
    contentMargin: {
      marginLeft: "10px",
      width: "100%",
    },
  };

  const [user, setUser] = useState(async () => {
    await UserApi.myInfo().then((res) => {
      setUser(res.data.body.user);
    });
  });

  return (
    <>
      <nav>
        <div className="nav-container">
          <div className="nav-1">
            <a href={`/post`}>
              <img
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Instagram_logo.svg/320px-Instagram_logo.svg.png"
                alt="logo_img"
              />
            </a>
          </div>
          <SearchBoxComponent />
          <div className="nav-2">
            <a href={`/post`}>
              <img
                src="https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png"
                alt="home"
              />
            </a>
            <img
              src="https://cdn3.iconfinder.com/data/icons/email-133/32/Email_paper_air_plane_airplane_send_message-512.png"
              alt="direct-message"
            />
            <button onClick={openModal}>
              <img
                src="https://cdn2.iconfinder.com/data/icons/font-awesome/1792/plus-square-o-512.png"
                alt="plus"
              />
            </button>
            <SavePostModal
              open={modalOpen}
              close={closeModal}
              header="Create new post"
            />
            <img
              src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/explore.png"
              alt="search"
            />
            <button onClick={getNotifications}>
              <img
                src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
                alt="like"
              />
            </button>
            {notificationList ? (
              <div className="notify_area">
                {notificationList.map((notification, index) => (
                  <div
                    id={index}
                    className="notify_box"
                    onClick={(event) =>
                      clickNotificationMessage(notification.post)
                    }
                  >
                    <p
                      dangerouslySetInnerHTML={{
                        __html: convertNotificationMessage(notification),
                      }}
                    ></p>
                    {getNotificationMessage(notification)}
                  </div>
                ))}
              </div>
            ) : null}
            <a href={`/${user.userName}/`}>
              <img
                className="pic"
                src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                alt="mypage"
              />
            </a>
          </div>
        </div>
      </nav>
      <PostModal
        open={postModalOpen}
        close={closePostModal}
        post={selectedPost}
      ></PostModal>
      <div style={styles.contentDiv}></div>
    </>
  );
};

export default NavComponent;
