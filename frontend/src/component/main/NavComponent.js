import React, { useEffect, useState, useRef } from "react";
import SavePostModal from "./SavePostModal";
import SearchBoxComponent from "./SearchBoxComponent";
import NotificationApi from "../../lib/api/notification";
import "../../styles/alarm.css";

const NavComponent = () => {
  // useState를 사용하여 open상태를 변경한다. (open일때 true로 만들어 열리는 방식)
  const [modalOpen, setModalOpen] = useState(false);
  const [notificationState, setNotificationState] = useState(false);
  const [notificationList, setNotificationList] = useState(null);

    const openModal = () => {
        document.body.style= `overflow: hidden`;
        setModalOpen(true);
    };
    const closeModal = () => {
        setModalOpen(false);
    };

  const getNotificationState = async () => {
    await NotificationApi.getNotReadNotificationsCount().then((res) => {
      if (res.data >= 1) {
        setNotificationState(true);
      }
    });
  };
  const [isHovered, setIsHovered] = useState(false);

  const handleMouseEnter = () => {
    showkNotificaitonList();
  };

  const handleMouseLeave = () => {
    notShowNotificationList();
  };
  const notificationListRef = useRef();
  const getNotifications = async () => {
    await NotificationApi.getNotifications().then((res) => {
      setNotificationList(res.data);
      showkNotificaitonList();
    });
  };

  const showkNotificaitonList = () => {
    console.log("show");
    notificationListRef.current.style.display = "block";
  };

  const notShowNotificationList = () => {
    console.log("none");
    notificationListRef.current.style.display = "none";
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

  return (
    <>
      <nav>
        <div className="nav-container">
          <div className="nav-1">
            <img
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Instagram_logo.svg/320px-Instagram_logo.svg.png"
              alt="logo_img"
            />
          </div>
          <SearchBoxComponent />
          <div className="nav-2">
            <img
              src="https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png"
              alt="home"
            />
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
              <div
                onMouseEnter={() => showkNotificaitonList()}
                onMouseLeave={() => notShowNotificationList()}
                className="container"
              >
                {notificationList != null && (
                  <ul ref={notificationListRef} className="list">
                    {notificationList.map((item, idx) => {
                      return <li key="idx">{item.message}</li>;
                    })}
                  </ul>
                )}
              </div>
              {notificationState && <span className="circle"></span>}
              {/* {notificationList != null && (
                <div className="search_div">
                  <div className="s_d">
                    {notificationList.map((item, idx) => {
                      return <div key={idx}>{item.message}</div>;
                    })}
                  </div>
                </div>
              )} */}
            </button>
            <img
              className="pic"
              src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
              alt="mypage"
            />
          </div>
        </div>
      </nav>
      <div style={styles.contentDiv}></div>
    </>
  );
};

export default NavComponent;
