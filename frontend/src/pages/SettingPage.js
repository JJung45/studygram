import React, {useState} from "react";
import NavContainer from "../container/main/NavContainer";
import SettingsComponent from "../component/auth/SettingsComponent";
import UserApi from "../lib/api/user";

const SettingPage = () => {
    const [info, setInfo] = useState(async () => {
        await UserApi.myInfo().then((res) => {
            setInfo(res.data.body.user);
        });
    });

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
