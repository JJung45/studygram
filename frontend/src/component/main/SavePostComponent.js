import React, { useState, useEffect } from "react";
import PostApi from "../../lib/api/post";

const SavePostComponent = () => {
  const [data, setData] = useState(null);
  const [hasMore, setHasMore] = useState(true);

   // PostApi.addPost()
     // .then(res => {
       // setData(res.data);
      //});

  return (
    <div>
        <form name="savePost">
            <input type="hidden" name="user_id" value="session test id" />
            <input name="content" />
            <input type="submit"/>
        </form>
    </div>
  );
};

export default SavePostComponent;
