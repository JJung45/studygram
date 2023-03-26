import React, {useState, useEffect} from 'react';
import searchAPI from "../../lib/api/search"
import {Link} from "react-router-dom";

const SearchBoxComponent = () => {
    const [keyword, setKeyword] = useState('');
    const [searchList, setSearchList] = useState([]);

    const onKeywordChange = (e) => {
        console.log('검색', keyword);
        setKeyword(e.target.value);
        if(keyword.length === 0) {
            setSearchList([]);
        }

    };

    useEffect(() => {
        console.log(keyword);
        if(keyword.length === 0) {
            setSearchList([]);
        }
        else {
            searchAPI.search(keyword, 2)
                .then((res) => {
                    console.log('검색결과: ', res.data.body.searchList);
                    const resultList = res.data.body.searchList;
                    setSearchList(resultList);
                });
        }
    }, [keyword]);

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
            {searchList.map((item, idx) => {
                return (
                    <>
                        <div className="search-result" key={idx + item.idx}>
                            <Link to={"/" + item.userName}>
                                {/*뒤로가기 시 자동완성 남아있는 경우 비워주는 코드*/}
                                <div className="recommend-friend-profile" onClick={() => setSearchList([])}>
                                    <img
                                        className="img-profile"
                                        src={item.profileImageUrl}
                                        alt="프로필 사진"
                                    />
                                    <div className="profile-text">
                                        <span className="userID point-span">{item.userName}</span>
                                    </div>
                                </div>
                            </Link>

                        </div>
                    </>
                )
            })}
        </div>
    )
};

export default SearchBoxComponent;