import React,{useState,useEffect} from "react";
import BoardComponent from "../auth/BoardComponent";
import SavedComponent from "../auth/SavedComponent";
import TagComponent from "../auth/TagComponent";
import {Link} from "react-router-dom";
import searchAPI from "../../lib/api/search";
import "../../styles/Post.css"

// TagList, AccountList
const SearchListComponent = () => {
    const [keyword, setKeyword] = useState('');
    const [tagList, setTagList] = useState([]);
    const [userList, setUserList] = useState([]);

    const onKeywordChange = (e) => {
        console.log('검색', keyword);
        setKeyword(e.target.value);
        if(keyword.length === 0) {
            setTagList([]);
            setUserList([]);
        }
    }
    useEffect(() => {
        console.log(keyword);
        if(keyword.length === 0) {
            setTagList([]);
            setUserList([]);
        }
        else {
            searchAPI.searchBoth(keyword)
                .then((res) => {
                    console.log('검색결과: ', res.data.body.searchList);
                    // user || tag
                    const resultList = res.data.body.searchList;
                    const tags = resultList.tagList;
                    const users = resultList.userList;
                    setTagList(tags.length > 0 ? tags : []);
                    setUserList(users.length > 0 ? users : [])
                });
        }
    }, [keyword]);

    return (
        <>
            <div>
            <input
                id="searchInput"
                type="search"
                className="input-search"
                placeholder="검색"
                onChange={onKeywordChange}
                value={keyword}
            />
            </div>
            {userList.map((item, idx) => {
                    return (
                        <>
                            <div className="" key={idx + item.idx}>
                                <Link to={"/" + item.userName} style={{textDecoration: "none", color: "black", fontSize: "13px"}}>
                                    {/*뒤로가기 시 자동완성 남아있는 경우 비워주는 코드*/}
                                    <div className="" onClick={() => setUserList([])}>
                                        <img
                                            className="img-profile"
                                            src={item.profileImageUrl}
                                            alt="프로필 사진"
                                        />
                                        <div className="">
                                            <span className="">{item.userName}</span>
                                        </div>
                                        <div className="">
                                            <span className="">{item.profileMsg}</span>
                                            <span className="">{item.followersCnt}</span>
                                        </div>
                                    </div>
                                </Link>

                            </div>
                        </>
                    )
                })}
            {tagList.map((item, idx) => {
                return (
                    <>
                        <div className="" key={idx + item.idx}>
                            <Link to={"/tagPost/"+item.idx} style={{textDecoration: "none", color: "black", fontSize: "13px"}}>
                                {/*뒤로가기 시 자동완성 남아있는 경우 비워주는 코드*/}
                                <div className="" onClick={() => setTagList([])}>
                                    <img
                                        className=""
                                        src=""
                                        alt="#아이콘"
                                    />
                                    <div className="">
                                        <span className="">#{item.content}</span>
                                    </div>
                                </div>
                            </Link>

                        </div>
                    </>
                )
            })}
        </>
    )


};

export default SearchListComponent;