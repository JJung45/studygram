import React, {useState, useEffect} from "react";
import SearchApi from "../../lib/api/search";

// TagList, AccountList
const SearchListComponent = (tagIdx) => {
    const {tagIdx} = props;
    const statusType = {
        DATE: "date",
        LIKE: "like",
    };

    const [type, setType] = useState(statusType.LIKE);
    if(type == statusType.DATE) {
        SearchApi.searchPosts(tagIdx, 1)
            .then(res => {

            })
    }
    else {
        SearchApi.searchPosts(tagIdx, 0)
            .then(res => {

            })
    }

    return(
        <div>

        </div>
    )

};

export default SearchListComponent;