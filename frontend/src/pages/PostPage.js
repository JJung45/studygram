import React from "react";
import NavContainer from "../container/main/NavContainer";
import PostContainer from "../container/main/PostContainer";
import AsideContainer from "../container/main/AsideContainer";
import "../styles/Post.css";
import SidebarComponent from "../component/main/SidebarComponent";

const PostPage = () => {
  const userIdx = window.localStorage.getItem("userIdx");
  

  return (
    <>
        {/*<SidebarComponent width={320}/>*/}
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
