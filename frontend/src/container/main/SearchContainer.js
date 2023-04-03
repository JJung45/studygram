import React from "react";
import SearchBoxComponent from "../../component/main/SearchBoxComponent";
import SearchPage from "../../pages/SearchPage";

const SearchContainer = () => {
    return (
        <div>
            <SearchBoxComponent></SearchBoxComponent>
            <br/>
            <SearchPage></SearchPage>
        </div>
    );
};
export default SearchContainer;