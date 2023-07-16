import React from "react";
import NavContainer from "../container/main/NavContainer";
import SettingsComponent from "../component/auth/SettingsComponent";

const SettingPage = () => {
    return (
        <>
            <NavContainer />
            <main>
                <SettingsComponent />
            </main>
        </>
    );
};

export default SettingPage;
