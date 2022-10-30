import React, { useEffect } from "react";
import "./styles/App.css";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import { Route, Link, Routes } from "react-router-dom";
import ForgetPasswordPage from "./pages/ForgetPasswordPage";
import MyPage from "./pages/MyPage";
import PostPage from "./pages/PostPage";
import CommentPage from "./pages/CommentPage";
import SavePostPage from "./pages/SavePostPage";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" exact={true} element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/oauth2/redirect" element={<MyPage />} />
        <Route path="/forgetpassword" element={<ForgetPasswordPage />} />
        <Route path="/post/save" element={<SavePostPage />} />
        <Route path="/post" element={<PostPage />} />
        <Route path="/post/:postId" element={<SavePostPage />} />
        <Route path="/comment" element={<CommentPage />} />
      </Routes>
    </>
  );
}

export default App;
