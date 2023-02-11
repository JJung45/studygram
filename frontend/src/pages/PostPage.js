import React from "react";
import NavContainer from "../container/main/NavContainer";
import PostContainer from "../container/main/PostContainer";
import AsideContainer from "../container/main/AsideContainer";
import "../styles/Post.css";

const PostPage = () => {
  return (
    <>
      <NavContainer />
      <main>
        <PostContainer />
        <AsideContainer />
      </main>
    </>
  );
};

export default PostPage;
