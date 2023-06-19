import React, {useState, useEffect, useRef} from "react";
import SearchApi from "../../lib/api/search";
import {useParams, useSearchParams} from "react-router-dom";
import PostModal from "../auth/PostModal";

// TagList, AccountList
const SearchPostComponent = () => {
    const orderingType = {
        LIKE: 0,
        DATE: 1,
    };
    const {tagIdx} = useParams();
    const [type, setType] = useState(orderingType.LIKE);
    const [posts, setPosts] = useState([]);
    const [selectedPost, setSelectedPost] = useState(null);
    const [modalOpen, setModalOpen] = useState(false);

    useEffect(() => {
        SearchApi.searchPosts(tagIdx, type)
            .then(res => {
                console.log('태그아이디:', tagIdx, ' 결과:', res.data);
                setPosts(res.data.body.postList);
            })
    }, [tagIdx]);

    useEffect(() => {
        SearchApi.searchPosts(tagIdx, type)
            .then(res => {
                console.log('정렬타입:', orderingType, ' 결과:', res.data);
                setPosts(res.data.body.postList);
            })
    }, [type]);

    const openModal = (post) => {
        setModalOpen(true);
        setSelectedPost(post);
    };
    const closeModal = () => {
        setModalOpen(false);
        setSelectedPost(null);
    };

    const modalRef = useRef<HTMLDivElement>(null);
    const modalOutSideClick = (e:any) => {
        if(modalRef.current === e.target) {
            setModalOpen(false)
        }  }

    return (
            <div className="feed" style={{borderTop: "none"}}>
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
                    <div className="container">
                        <div className="gallery">
                            {posts.length > 0 ?
                                Object.values(posts).map((post, index) => (
                                    // <div className="gallery-item" tabIndex="0" onClick={() => setModalOpen(true)} key={post.idx + index}>
                                    <div className="gallery-item" tabIndex="0" onClick={() => openModal(post)} key={post.idx + index}>
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
                                                <li className="gallery-item-comments">
                                                    <span className="visually-hidden">Comments:</span>
                                                    <i className="ri-chat-3-fill ri-admin-line ri-xl"></i>{" "}
                                                    {post.commentCnt}
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                )) : (
                                    <div style={{textAlign: "center", marginTop: "20px"}}>
                                        게시물 없음
                                    </div>
                                )
                            }
                            {/*{modalOpen &&*/}
                            {/*    <PostModal*/}
                            {/*        open={modalOpen}*/}
                            {/*        close={closeModal}*/}
                            {/*        post={selectedPost}*/}
                            {/*        ref={modalRef}*/}
                            {/*        modalOutSideClick={modalOutSideClick}*/}
                            {/*    >*/}
                            {/*    </PostModal>*/}
                            {/*}*/}


                            {/*모달을 왜 여기에 둬야 닫힐까..?*/}
                            <PostModal
                                open={modalOpen}
                                close={closeModal}
                                post={selectedPost}>
                            </PostModal>
                        </div>
                    </div>
                </div>
            </div>
    );

};

export default SearchPostComponent;