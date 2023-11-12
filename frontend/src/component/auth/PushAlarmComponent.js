import React, {useState, useEffect, useRef} from "react";
import "../../styles/Setting.css";
import {useLocation} from "react-router-dom";

const PushAlarmComponent = () => {
    const location = useLocation();
    const info = location.state?.info;

    return (
        <div>
            <h1>푸시알림</h1>
        </div>
    );
}
export default PushAlarmComponent;