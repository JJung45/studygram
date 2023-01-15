import React from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom";

const RedirectPage = () => {
  const search = useLocation().search;
  const token = new URLSearchParams(search).get("token");
  const navigate = useNavigate();
  if (token) {
    console.log(token);
    window.localStorage.setItem("jwtToken", token);
    return <Navigate to="/post" />;
  }

  return <div>fail</div>;
};

export default RedirectPage;
