import React, {useEffect, useState} from "react";

/* 계정 추천 컴포넌트 */
const Suggestions = (props) => {
    const {eleImg, eleName, eleIdx, arrIdx, isFollow, followClick} = props;

    useEffect(() => {
    }, [props]);

    return (
        <li key={arrIdx}>
            <div className="recommend-friend-profile">
                <img
                    className="img-profile"
                    src={eleImg}
                    alt="프로필 사진"
                />
                <div className="profile-text">
                    <span className="userID point-span">{eleName}</span>
                </div>
            </div>
            <div style={{
                color: isFollow ? 'gray' : '#0095f6'
            }} className="btn-follow" onClick={() => followClick(eleIdx, arrIdx)}>팔로우
            </div>
        </li>
    );
};
export default Suggestions;