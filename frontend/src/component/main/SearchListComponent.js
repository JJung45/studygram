import React, {useState, useEffect, Fragment} from "react";
import {Link} from "react-router-dom";
import searchAPI from "../../lib/api/search";
import "../../styles/search.scss"

// TagList, AccountList
const SearchListComponent = () => {
    const [keyword, setKeyword] = useState('');
    const [tagList, setTagList] = useState([]);
    const [userList, setUserList] = useState([]);

    const onKeywordChange = (e) => {
        console.log('검색', keyword);
        setKeyword(e.target.value);
        if (keyword.length === 0) {
            setTagList([]);
            setUserList([]);
        }
    }
    useEffect(() => {
        console.log(keyword);
        if (keyword.length === 0) {
            setTagList([]);
            setUserList([]);
        } else {
            searchAPI.searchBoth(keyword)
                .then((res) => {
                    console.log('검색결과: ', res.data.body.searchList);
                    // user || tag
                    const resultList = res.data.body.searchList;
                    const tags = resultList.tagList;
                    const users = resultList.userList;
                    setTagList(tags.length > 0 ? tags : []);
                    setUserList(users.length > 0 ? users : []);
                });
        }
    }, [keyword]);

    return (
        <>
            <div className="innerSearch">
                <input
                    id="searchInput"
                    type="search"
                    className="input-search"
                    placeholder="검색"
                    onChange={onKeywordChange}
                    value={keyword}
                />
            </div>
            {userList.length === 0 && tagList.length === 0 ? (
                <div className="">
                    <span>검색어를 입력하세요</span>
                </div>
            ) : (
                <div className="search_div" style={{position: "relative", left: "0px", width: "300px"}}>
                    <div className="s_d">
                        {userList.map((item, idx) => {
                                return (
                                    <>
                                        <div className="s_d_peo" key={idx + item.idx}>
                                            <Link className="s_d_p" to={"/" + item.userName}
                                                  style={{textDecoration: "none", color: "black", fontSize: "13px"}}>
                                                {/*뒤로가기 시 자동완성 남아있는 경우 비워주는 코드*/}
                                                <Fragment onClick={() => setUserList([])}>
                                                    <img
                                                        src={item.profileImageUrl}
                                                    />
                                                    <div className="s_d_c">
                                                        <span className="s_d_username">{item.userName}</span>
                                                        <span className="">{item.profileMsg}</span>
                                                        <span className="">{item.followersCnt}</span>
                                                    </div>
                                                </Fragment>
                                            </Link>
                                        </div>
                                    </>
                                )
                            })}
                        {tagList.map((item, idx) => {
                                return (
                                    <>
                                        <div className="s_d_peo" key={idx + item.idx}>
                                            <Link className="s_d_p" to={"/tagPost/" + item.idx}
                                                  style={{textDecoration: "none", color: "black", fontSize: "13px"}}>
                                                {/*뒤로가기 시 자동완성 남아있는 경우 비워주는 코드*/}
                                                <Fragment onClick={() => setTagList([])}>
                                                    <img
                                                        src=""
                                                    />
                                                    <div className="s_d_c">
                                                        <span className="s_d_username">{item.content}</span>
                                                        <span>
                                                        </span>
                                                    </div>
                                                </Fragment>
                                            </Link>

                                        </div>
                                    </>
                                )
                            })}
                    </div>
                </div>
                    )
                })
            )}
        </>
    )


};

export default SearchListComponent;