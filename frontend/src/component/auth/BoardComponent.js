import React, { useState } from "react";
import { useLocation } from "react-router-dom";

import PostApi from "../../lib/api/post";
import PostModal from "./PostModal";

const BoardComponent = () => {
  const pathname = useLocation().pathname;
  const userName = pathname.split("/")[1];

  const [posts, setPosts] = useState(async () => {
    await PostApi.getPostsByUserName(userName).then((res) => {
      setPosts(res.data);
    });
  });
  const [post, setPost] = useState(null);

  const [modalOpen, setModalOpen] = useState(false);

  const openModal = async (post) => {
    setModalOpen(true);
    setPost(post);
  };

  const closeModal = async () => {
    await PostApi.getPostsByUserName(userName).then((res) => {
      setPosts(res.data);
    });
    setModalOpen(false);
    setPost(null);
  };

  return (
    <div className="container">
      <div className="gallery">
        {posts &&
          Object.values(posts).map((post, index) => (
            <div
              className="gallery-item"
              tabIndex={post.index}
              key={post.index}
              onClick={(event) => {
                openModal(post, event);
              }}
            >
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
                    <img
                      className="icon-react"
                      src="https://cdn0.iconfinder.com/data/icons/font-awesome-solid-vol-1/512/comment-512.png"
                      alt="말풍선"
                    />
                    {post.commentCnt}
                  </li>
                </ul>
              </div>
            </div>
          ))}
      </div>
      <PostModal
        open={modalOpen}
        close={closeModal}
        header="포스트"
        post={post}
      ></PostModal>
      {/* <div className="loader"></div> */}
    </div>
  );
};

export default BoardComponent;
