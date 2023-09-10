import React, { useState } from "react";
import "./styles/App.css";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import { Route, Link, Routes } from "react-router-dom";
import ForgetPasswordPage from "./pages/ForgetPasswordPage";
import MyPage from "./pages/MyPage";
import PostPage from "./pages/PostPage";
import CommentPage from "./pages/CommentPage";
import RedirectPage from "./pages/RedirectPage";
import SearchPage from "./pages/SearchPage";
import SettingPage from "./pages/SettingPage";
import PushAlarmComponent from "./component/auth/PushAlarmComponent";
import ProfileEditComponent from "./component/auth/ProfileEditComponent";
import ActivityComponent from "./component/auth/ActivitiyComponent";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" exact={true} element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/oauth2/redirect" element={<RedirectPage />} />
        <Route path="/forgetpassword" element={<ForgetPasswordPage />} />
        <Route path="/post" element={<PostPage />} />
        <Route path="/comment" element={<CommentPage />} />
        <Route path="/:userName/" element={<MyPage />} />
        <Route path="/tagPost/:tagIdx" element={<SearchPage/>} />
        <Route path="/accounts" element={<SettingPage/>}>
          <Route path="edit" element={<ProfileEditComponent/>}/>
          <Route path="push" element={<PushAlarmComponent/>}/>
          <Route path="activity" element={<ActivityComponent/>}/>
        </Route>
      </Routes>
    </>
  );
}

export default App;
