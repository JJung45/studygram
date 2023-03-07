import React, {useEffect, useState} from "react";
import followAPI from "../../lib/api/follow";

/* 계정 추천 컴포넌트 */
const Suggestions = () => {
    const [isFollow, setIsFollow] = useState(false);
    const [suggestions, setSuggestions] = useState([]);
    const [follow, setFollow] = useState({
        toUserIdx : '',
        isFollow : false,
    })

    useEffect(() => {
        followAPI.getSuggestions(5)
            .then((result) => {
                setSuggestions(result.data)

                result.data.map((i) => followCheck(i.idx));
            });

    }, []);


    const followCheck = (toUserIdx) => {
        followAPI.chkFollow(toUserIdx).then((res) => {
            console.log("rest", res);
            if (res.data) {
                // setIsFollow(true);
                setFollow({
                    ...follow,
                    toUserIdx: toUserIdx,
                    isFollow: true,
                });
            }
            else {
                setIsFollow(false);
                setFollow({
                    ...follow,
                    toUserIdx: toUserIdx,
                    isFollow: false,
                });
            }
        });
    };

    const followClick = async(toUserIdx) => {
        followCheck(toUserIdx);
        // console.log("followingStat:", isFollow);
        // setIsFollow(current => !current);
        // 추천된 user 정보만 가져오기

        if (!isFollow) {
            await followAPI
                .follow({
                    toUserIdx: toUserIdx,
                })
                .then(() => setIsFollow(true));
        } else {
            await followAPI.unfollow(toUserIdx).then(() => setIsFollow(false));
        }

    };

    return (
        // <div className="mt-4 ml-10">
        //     <div className="flex justify-between text-sm nb-d-5">
        //         <h3 className="text-sm font-bold text-gray-400">Suggestions for you</h3>
        //         <button className="text-gray-600 font-semibold">See All</button>
        //     </div>
        //     {suggestions.map((user) => (
        //         <div key={user.idx} className="flex items-center justify-between">
        //             <img
        //                 className="w-10 h-10 rounded-full border p-[2px]"
        //                 src={user.profile_image_url}
        //                 alt=""
        //             />
        //             <div className="flex-1 ml-4">
        //                 <h2 className="font-semibold text-sm">{user.username}</h2>
        //                 <h3 className="text-xs text-gray-400">{user.fullname}</h3>
        //             </div>
        //             <button className="text-blue-400 text-sm font-bold">팔로우</button>
        //         </div>
        //     ))}
        // </div>
        <div className="section-recommend">
            <div className="menu-title">
                <span className="sub-title">회원님을 위한 추천</span>
                <span className="find-more">모두 보기</span>
            </div>
            <ul className="recommend-list">
                {suggestions.map((user, index) => (
                    <li key={index}>
                        <div className="recommend-friend-profile">
                            <img
                                className="img-profile"
                                src={user.profileImageUrl}
                                alt="프로필 사진"
                            />
                            <div className="profile-text">
                                <span className="userID point-span">{user.userName}</span>
                                {/*<span className="sub-span">hakyeong님 외 2명이 팔로우합니다</span>*/}
                            </div>
                        </div>
                        <div style={{
                            color: isFollow ? 'gray' : '#0095f6'
                        }} className="btn-follow" onClick={() => followClick(user.idx)}>팔로우
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};
export default Suggestions;