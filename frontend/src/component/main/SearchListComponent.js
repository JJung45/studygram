import React from "react";

// TagList, AccountList
const SearchListComponent = ({searchList}) => {
    const list = searchList.map((content, index) => <li key={index}>{content}</li>)
    return <ul>{list}</ul>;
};

export default SearchListComponent;