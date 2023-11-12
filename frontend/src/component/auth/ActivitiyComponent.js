import React, {useState, useEffect, useRef} from "react";
import "../../styles/Setting.css";
import {useLocation} from "react-router-dom";

const ActivityComponent = () => {
    /**
     * '프로필 편집' 버튼 클릭 시 변경되는 화면 컴포
     * 푸시 알림
     */

    const location = useLocation();
    const info = location.state?.info;

    return (
        <div>
            <h1>활동</h1>
        </div>
    );
}
export default ActivityComponent;