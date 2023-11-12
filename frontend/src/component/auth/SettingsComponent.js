import React, {useState, useEffect, useRef,} from "react";
import {Link, Navigate, Outlet, Route, Routes, useLocation} from "react-router-dom";
import "../../styles/Setting.css";
import UserApi from "../../lib/api/user";
import ProfileEditComponent from "./ProfileEditComponent";
import PushAlarmComponent from "./PushAlarmComponent";

const SettingsComponent = () => {
    /**
     * '프로필 편집' 버튼 클릭 시 변경되는 화면 컴포
     * 프로필편집
     * 1. 프로필 사진, 아이디, 프로필 사진 바꾸기
     * 2. 소개
     * 3. 성별
     * 4. 공개/비공개
     * 알림
     * 활동 (나의 댓글, 좋아요, 저장한 게시물)
     * @returns {JSX.Element}
     * @constructor
     */
    //
    const location = useLocation();
    const info = location.state?.info;

// 느린데,, 왜그런지 확인 필요
//     const [info, setInfo] = useState(async () => {
//         await UserApi.myInfo().then((res) => {
//             setInfo(res.data.body.user);
//         });
//     });

    return (
        <section className="section-info">
            <main className="main-info">
                <h1 className="_afws">설정</h1>
                <div className="_ab81 _ab82">
                    <ul className="_ab85">
                        <li><Link to="edit" state={{info : info}}>프로필 편집</Link></li>
                        <li><Link to="push" state={{info : info}}>푸시 알림</Link></li>
                        <li><Link to="activity" state={{info : info}}>활동</Link></li>
                    </ul>
                </div>
                <div>
                    <Outlet/>
                </div>
            </main>
        </section>
    );
}
export default SettingsComponent;