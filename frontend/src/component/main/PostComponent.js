import React, { useState, useEffect } from "react";
import Post from "./Post";
import InfiniteScroll from "react-infinite-scroll-component";
import PostApi from "../../lib/api/post";

const PostComponent = () => {
  const dummy = [
    {
      id: "minchoi",
      post: "",
      comment: "",
    },
    {
      id: "headilee",
      post: "",
      comment: "",
    },
    {
      id: "jhk_",
      post: "",
      comment: "",
    },
  ];

  const dummy2 = [
    {
      id: "minchoi2",
      post: "",
      comment: "",
    },
    {
      id: "headilee2",
      post: "",
      comment: "",
    },
    {
      id: "jhk2",
      post: "",
      comment: "",
    },
  ];

  const [data, setData] = useState(dummy);
  const [hasMore, setHasMore] = useState(true);
  // useEffect(() => {
  //   nextData();
  // }, []);

  const nextData = () => {
    PostApi.getPosts();
  };

  return (
    <div id="scrollArea" class="feeds">
      <InfiniteScroll
        dataLength={data ? data.length : 1}
        next={nextData}
        hasMore={hasMore}
        loader={<h4>Loading...</h4>}
      >
        {data?.map((data, index) => (
          <Post data={data} key={index}></Post>
        ))}
      </InfiniteScroll>
    </div>
  );
};

export default PostComponent;
