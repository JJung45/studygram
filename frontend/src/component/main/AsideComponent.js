import React, {useEffect, useState} from "react";
import styled from "styled-components";
import followAPI from "../../lib/api/follow";
import {Navigate, useNavigate} from "react-router-dom";
import Suggestions from "./Suggestions";

const AsideComponent = () => {
    // header에서 가져옴
    const userIdx = window.localStorage.getItem("userIdx");
    const [suggestions, setSuggestions] = useState([]);
    const [activeBtnArr, setActiveBtnArr] = useState(Array(suggestions.length).fill(false));

    useEffect(() => {
        followAPI.getSuggestions(userIdx)
            .then((result) => {
                setSuggestions(result.data)
                console.log('추천 목록:', result.data);

                result.data.map((i) => followCheck(i.idx));
            });

    }, []);


    const followCheck = (toUserIdx) => {
        followAPI.chkFollow(toUserIdx).then((res) => {
            return res.data;
        });
    };

    const followClick = async (toUserIdx, arrIdx) => {
        console.log('배열 인덱스:', arrIdx, ', user아이디: ', toUserIdx);
        // setIsFollow(activeBtnArr[arrIdx]);
        if (!followCheck(toUserIdx)) {
            await followAPI.follow(toUserIdx);
            activeBtnArr[arrIdx] = true;
        } else {
            await followAPI.unfollow(toUserIdx);
        }
    };

    return (
        <div className="main-right">
            <div className="myProfile">
                <a href="/myPage">
                    <img
                        className="pic"
                        src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png"
                        alt="minchoi 프로필 사진"
                    />
                </a>
                <div>
          <span className="userID point-span">
            <a href="/myPage">minchoi</a>
          </span>
                    <span className="sub-span">Minkyeong Choi</span>
                </div>
            </div>
            <Suggestions/>
            <div className="section-recommend">
                <div className="menu-title">
                    <span className="sub-title">회원님을 위한 추천</span>
                    <span className="find-more">모두 보기</span>
                </div>
                <ul className="recommend-list">
                    {suggestions.map((ele, index) => (
                        <Suggestions
                            key={index}
                            arrIdx={index}
                            // isFollow={isFollow[index]}
                            isFollow={activeBtnArr[index]}
                            followClick={followClick}
                            eleIdx={ele.idx}
                            eleName={ele.username}
                            eleImg={ele.profileImageUrl}
                        />
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default AsideComponent;
