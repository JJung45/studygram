import React from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom";
import AuthApi from "../lib/api/auth";
import {
  NotificationContainer,
  NotificationManager,
} from "react-notifications";

const RedirectPage = () => {
  const search = useLocation().search;
  const urlSearchParams = new URLSearchParams(search);
  const token = urlSearchParams.get("token");
  const userIdx = urlSearchParams.get("userIdx");
  const navigate = useNavigate();
  const subscribeSse = async () => {
    try {
      const eventSource = new EventSource(
        `${process.env.REACT_APP_URL}/api/subscribe/${userIdx}`
      );

      eventSource.onmessage = async (event) => {
        const res = await event.data;
        if (res != "dummy") {
          alert(res);
        }
      };
    } catch (error) {}
  };

  if (token) {
    window.localStorage.setItem("jwtToken", token);
    window.localStorage.setItem("userIdx", userIdx);
    subscribeSse();

    return <Navigate to="/post" />;
  }

  return <div>fail</div>;
};

export default RedirectPage;
