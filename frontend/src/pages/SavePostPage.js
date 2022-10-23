import React from "react";
import NavContainer from "../container/main/NavContainer";
import AsideContainer from "../container/main/AsideContainer";
import "../styles/Post.css";
import SavePostComponent from "../component/main/SavePostComponent";

const PostPage = () => {
  return (
    <>
      <main>
        <SavePostComponent />
      </main>
    </>
  );
};

export default PostPage;
