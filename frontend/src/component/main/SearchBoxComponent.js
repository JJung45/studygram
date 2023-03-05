import React, {useState, useEffect} from 'react';
import searchAPI from "../../lib/api/search"

const SearchBoxComponent = () => {
    const [keyword, setKeyword] = useState('');
    const [searchList, setSearchList] = useState([]);

    const onKeywordChange = (e) => {
        console.log('검색', keyword);
        setKeyword(e.target.value);
    };

    useEffect(() => {
        // searchKeyword(keyword, 1);
        console.log(keyword);
        searchAPI.search(keyword, 1)
            .then((res) => {
                setSearchList(res.data);
            });
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
            <ul>
                {/*{searchList.map((row, index) => <li key={index}>{row.username}</li>)}*/}
            </ul>
        </div>
    )
};

export default SearchBoxComponent;