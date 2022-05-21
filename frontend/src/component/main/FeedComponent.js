import React, { useState, useEffect } from "react";
import Feed from "./Feed";
import InfiniteScroll from "react-infinite-scroll-component";
const FeedComponent = () => {
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
  useEffect(() => {
    nextData();
  }, []);

  const nextData = () => {
    if (data.length > 10) {
      setHasMore(false);
    }
    setData((prev) => {
      return prev.concat(dummy2);
    });
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
          <Feed data={data} key={index}></Feed>
        ))}
      </InfiniteScroll>
    </div>
  );
};

export default FeedComponent;
