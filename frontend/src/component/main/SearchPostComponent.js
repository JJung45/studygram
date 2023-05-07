import React, {useState, useEffect} from "react";
import SearchApi from "../../lib/api/search";
import {useParams} from "react-router-dom";

// TagList, AccountList
const SearchListComponent = (props) => {
    const {tagIdx} = useParams();
    const orderingType = {
        LIKE: "like",
        DATE: "date",
    };

    const [type, setType] = useState(orderingType.LIKE);
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        console.log('type', type)
        if(type === orderingType.LIKE) {
            SearchApi.searchPosts(tagIdx, 0)
                .then(res => {
                    console.log('인기순 정렬:', res.data);
                    setPosts(res.data);
                })
        }
        else {
            SearchApi.searchPosts(tagIdx, 1)
                .then(res => {
                    setPosts(res.data);

                })
        }
    }, [type]);

    return(
        <div>
            <div className="feed">
                <div className="feed-header">
                    <div
                        className="sub"
                        onClick={(event) => {
                            setType(orderingType.LIKE);
                        }}
                    >
                        <img
                            className="icon-react"
                            src="https://cdn1.iconfinder.com/data/icons/bootstrap-vol-3/16/grid-3x3-512.png"
                        ></img>
                        <span>인기순</span>
                    </div>
                    <div
                        className="sub"
                        onClick={(event) => {
                            setType(orderingType.DATE);
                        }}
                    >
                        <img
                            className="icon-react"
                            src="https://cdn0.iconfinder.com/data/icons/ui-interface-6/24/bookmark-512.png"
                        ></img>
                        <span>최신순</span>
                    </div>
                </div>
                <div className="main-box">
                    <div>
                        <div className="container">
                            <div className="gallery">
                                {posts &&
                                    Object.values(posts).map((post, index) => (
                                        <div className="gallery-item" tabIndex="0">
                                            <img
                                                src={post.storePath ?? "https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"}
                                                className="gallery-image"
                                                alt=""
                                            />

                                            <div className="gallery-item-type">
                                                <span className="visually-hidden">Gallery</span>
                                                <i className="fas fa-clone" aria-hidden="true"></i>
                                            </div>

                                            <div className="gallery-item-info">
                                                <ul>
                                                    <li className="gallery-item-likes">
                                                        <span className="visually-hidden">Likes:</span>
                                                        <i className="ri-heart-3-fill ri-admin-line ri-xl"></i>{" "}
                                                        {post.likeCnt}
                                                    </li>
                                                    {/*<li className="gallery-item-comments">*/}
                                                    {/*    <span className="visually-hidden">Comments:</span>*/}
                                                    {/*    <img*/}
                                                    {/*        className="icon-react"*/}
                                                    {/*        src="https://cdn0.iconfinder.com/data/icons/font-awesome-solid-vol-1/512/comment-512.png"*/}
                                                    {/*        alt="말풍선"*/}
                                                    {/*    />*/}
                                                    {/*    {posts.commentCnt}*/}
                                                    {/*</li>*/}
                                                </ul>
                                            </div>
                                        </div>
                                    ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )

};

export default SearchListComponent;