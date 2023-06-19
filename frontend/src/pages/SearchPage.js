import React, {useState, useEffect} from 'react';
import SearchPostComponent from "../component/main/SearchPostComponent";
import NavContainer from "../container/main/NavContainer";

const SearchPage = () => {
    return (
        <>
            <NavContainer/>
            <main>
                <SearchPostComponent/>
            </main>
        </>
    )
};
export default SearchPage;