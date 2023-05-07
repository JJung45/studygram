import React from "react";
import NavContainer from "../container/main/NavContainer";
import PostContainer from "../container/main/PostContainer";
import AsideContainer from "../container/main/AsideContainer";
import "../styles/Post.css";
import SidebarComponent from "../component/main/SidebarComponent";

const PostPage = () => {
  return (
    <>
        {/*<SidebarComponent width={320}/>*/}
        <NavContainer />
      <main>
        <PostContainer />
        <AsideContainer />
      </main>
    </>
  );
};

export default PostPage;
