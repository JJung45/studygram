import React, {useState} from "react";
import NavContainer from "../container/main/NavContainer";
import SettingsComponent from "../component/auth/SettingsComponent";
import UserApi from "../lib/api/user";
import {BrowserRouter, Link, Route, Router, Routes} from "react-router-dom";
import ProfileEditComponent from "../component/auth/ProfileEditComponent";
import PushAlarmComponent from "../component/auth/PushAlarmComponent";

const SettingPage = () => {
        const [info, setInfo] = useState(async () => {
        await UserApi.myInfo().then((res) => {
            setInfo(res.data.body.user);
        });
    });

        console.log('info', info);

    return (
        <>
            <NavContainer />
            <main>
                <SettingsComponent state={{info: info}}/>
            </main>
        </>
    );
};

export default SettingPage;
