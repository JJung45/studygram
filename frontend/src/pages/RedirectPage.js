import React from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom";

const RedirectPage = () => {
  const search = useLocation().search;
  const urlSearchParams = new URLSearchParams(search);
  const token = urlSearchParams.get("token");
  const userIdx = urlSearchParams.get("userIdx");
  const navigate = useNavigate();
  if (token) {
    console.log(token);
    window.localStorage.setItem("jwtToken", token);
    window.localStorage.setItem("userIdx", userIdx);
    return <Navigate to="/post" />;
  }

  return <div>fail</div>;
};

export default RedirectPage;
