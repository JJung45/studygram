import React from "react";
import NavContainer from "../container/main/NavContainer";
import PostContainer from "../container/main/PostContainer";
import AsideContainer from "../container/main/AsideContainer";
import "../styles/Post.css";

const PostPage = () => {
  const userIdx = window.localStorage.getItem("userIdx");
  

  return (
    <>
      <NavContainer />
      <main>
        <PostContainer />
        {
          userIdx === null
          ? null // TODO null일 때 css확인
          : <AsideContainer userIdx={userIdx}/>
       }
      </main>
    </>
  );
};

export default PostPage;
