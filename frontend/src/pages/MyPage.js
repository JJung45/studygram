import React from "react";
import MyPageContainer from "../container/auth/MyPageContainer";
import NavContainer from "../container/main/NavContainer";
import "../styles/MyPage.css";

const MyPage = () => {
  return (
    <>
      <NavContainer />
      <main>
        <MyPageContainer />
      </main>
    </>
  );
};

export default MyPage;
