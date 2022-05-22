import React from "react";
import NavContainer from "../container/main/NavContainer";
import FeedContainer from "../container/main/FeedContainer";
import AsideContainer from "../container/main/AsideContainer";
import "../styles/Feed.css";

const FeedPage = () => {
  return (
    <>
      <NavContainer />
      <main>
        <FeedContainer />
        <AsideContainer />
      </main>
    </>
  );
};

export default FeedPage;
