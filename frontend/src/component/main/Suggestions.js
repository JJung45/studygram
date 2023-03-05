import React, {useEffect, useState} from "react";
import followAPI from "../../lib/api/follow";

/* 계정 추천 컴포넌트 */
const SuggestionProps = {};
const Suggestions = () => {
    const [suggestions, setSuggestions] = useState([]);

    followAPI.getSuggestions(() => {

    }).then((res) => setSuggestions(res.data));

    useEffect(() => {
        const suggestions = [...Array(3)].map((i) => ({
            // TODO
        }));
    })
    return (
        <div className="mt-4 ml-10">
            <div className="flex justify-between text-sm nb-d-5">
                <h3 className="text-sm font-bold text-gray-400">Suggestions for you</h3>
                <button className="text-gray-600 font-semibold">See All</button>
            </div>
            {suggestions.map((profile) => (
                <div key={profile.id} className="flex items-center justify-between">
                    <img
                        className="w-10 h-10 rounded-full border p-[2px]"
                        src={profile.avatar}
                        alt=""
                    />
                    <div className="flex-1 ml-4">
                        <h2 className="font-semibold text-sm">{profile.username}</h2>
                        <h3 className="text-xs text-gray-400">{profile.email}</h3>
                    </div>
                    <button className="text-blue-400 text-sm font-bold">Follow</button>
                </div>
            ))}
        </div>
    // <div className="section-recommend">
    //     <div className="menu-title">
    //         <span className="sub-title">회원님을 위한 추천</span>
    //         <span className="find-more">모두 보기</span>
    //     </div>
    //     <ul className="recommend-list">
    //         <li>
    //             <div className="recommend-friend-profile">
    //                 <img
    //                     className="img-profile"
    //                     src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
    //                     alt="heaji님의 프로필 사진"
    //                 />
    //                 <div className="profile-text">
    //                     <span className="userID point-span">hyeji</span>
    //                     <span className="sub-span">hakyeong님 외 2명이 팔로우합니다</span>
    //                 </div>
    //             </div>
    //             <div style={{
    //                 color: isFollow ? 'gray' : '#0095f6'
    //             }} className="btn-follow" onClick={() => followClick('27')}>팔로우</div>
    //         </li>
    //         <li>
    //             <div className="recommend-friend-profile">
    //                 <img
    //                     className="img-profile"
    //                     src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
    //                     alt="_jeongjaehyun님의 프로필 사진"
    //                 />
    //                 <div className="profile-text">
    //                     <span className="userID point-span">hakyeong</span>
    //                     <span className="sub-span">heaji님이 팔로우합니다</span>
    //                 </div>
    //             </div>
    //             <span className="btn-follow">팔로우</span>
    //         </li>
    //         <li>
    //             <div className="recommend-friend-profile">
    //                 <img
    //                     className="img-profile"
    //                     src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
    //                     alt="leehi_hi님의 프로필 사진"
    //                 />
    //                 <div className="profile-text">
    //                     <span className="userID point-span">test_test</span>
    //                     <span className="sub-span">heaji님 외 5명이 팔로우합...</span>
    //                 </div>
    //             </div>
    //             <span className="btn-follow">팔로우</span>
    //         </li>
    //     </ul>
    // </div>
    );
};
export default Suggestions;