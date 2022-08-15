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

  const [data, setData] = useState(null);
  const [hasMore, setHasMore] = useState(true);

  const nextData = () => {
    PostApi.getPosts()
      .then(res => {
        setData(res.data);
        console.log("data:", res.data);
      });
    
  };

  useEffect(() => {
    nextData();
  },[])

  return (
    <div id="scrollArea" className="feeds">
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