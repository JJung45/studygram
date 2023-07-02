import React, {useState, useEffect, Fragment} from 'react';
import searchAPI from "../../lib/api/search"
import {Link} from "react-router-dom";
import "../../styles/search.css";
import "../../module/utils/utils";
import {humanReadable} from "../../module/utils/utils";
import {FaHashtag} from "react-icons/fa";

const SearchBoxComponent = () => {
    const [keyword, setKeyword] = useState('');
    // const [searchList, setSearchList] = useState([]);
    const [tagList, setTagList] = useState([]);
    const [userList, setUserList] = useState([]);

    const onKeywordChange = (e) => {
        // console.log('검색', keyword);
        setKeyword(e.target.value);
        if(keyword.length === 0) {
            // setSearchList([]);
            setTagList([]);
            setUserList([]);
        }
    };

    useEffect(() => {
        // console.log('검색',keyword);
        if(keyword.length === 0) {
            // setSearchList([]);
            setTagList([]);
            setUserList([]);
        }
        else {
            // searchAPI.search(keyword, 1)
            //     .then((res) => {
            //         console.log('검색결과: ', res.data.body.searchList);
            //         const resultList = res.data.body.searchList;
            //         setSearchList(resultList.length > 0 ? resultList : []);
            //     });
            searchAPI.searchBoth(keyword)
                .then((res) => {
                    // console.log('검색결과: ', res.data.body.searchList);
                    // user || tag
                    const resultList = res.data.body.searchList;
                    const tags = resultList.tagList;
                    const users = resultList.userList;
                    setTagList(tags.length > 0 ? tags : []);
                    setUserList(users.length > 0 ? users : []);
                });
        }
    }, [keyword]);

    const resetResList = () => {
        setTagList([]);
        setUserList([]);
    }

    return (
        <div>
            <input
                id="searchInput"
                type="search"
                className="input-search"
                placeholder="검색"
                onChange={onKeywordChange}
                value={keyword}
            />
            {(userList.length > 0 || tagList.length > 0) &&
                <div className="search_div">
                    <div className="s_d">
                        <span className="s_header">{humanReadable(userList.length+tagList.length, 'results')}</span>
                        {tagList.map((item, idx) => {
                            return (
                                <div key={idx + item.idx}>
                                    <div className="s_d_peo" onClick={() => resetResList()}>
                                        <Link className="s_d_p" to={"/tagPost/" + item.idx}
                                              style={{textDecoration: "none", color: "black", fontSize: "13px"}}>
                                            {/*뒤로가기 시 자동완성 남아있는 경우 비워주는 코드*/}
                                            <Fragment>
                                                <FaHashtag style={{width: "30px", height: "30px", borderRadius: "50%"}}/>
                                                <div className="s_d_c">
                                                    <span className="s_d_username">{item.content}</span>
                                                    <span>관련 게시물 {item.postCnt}</span>
                                                </div>
                                            </Fragment>
                                        </Link>
                                    </div>
                                </div>
                            )
                        })}
                        {userList.map((item, idx) => {
                            return (
                                <div key={idx + item.idx}>
                                    <div className="s_d_peo" key={idx + item.idx} onClick={() => resetResList()}>
                                        <Link className="s_d_p" to={"/" + item.userName}>
                                            <Fragment>
                                                <img src={item.profileImageUrl}/>
                                                <div className="s_d_c">
                                                    <span className="s_d_username">{item.userName}</span>
                                                    <span>{item.fullName}</span>
                                                </div>
                                            </Fragment>
                                        </Link>
                                    </div>
                                </div>
                            )
                        })}
                    </div>
                </div>
            }
        </div>
    )
};

export default SearchBoxComponent;