import React, { useState, useEffect } from "react";
import PostApi from "../../lib/api/post";
import axios from 'axios';

const SavePostComponent = () => {
    const [id, setId] = useState('');
    const [content, setContent] = useState();
    const [userId, setUserId] = useState(24);
    const [post, setPost] = useState({
        "content" : "asdfsdfds",
        "userId"  : 24
    });

    
    const onClickSearch = async(post) => {
        // Client 에 Token 담긴 axios 사용
        await PostApi.addPost(post)
        .then(() => {
            document.location.href = 'http://localhost:3000/post'
        })
        .catch((err) => {
          console.log("Add post Error!", err);
        });
    
      };


  return (
    <div>
        <div>
            <form name="savePost" method="post">
                <input type="hidden" name="userId" value={userId}/>
                <input name="content" onChange={(event) => setContent(event.target.value)}/>
                <input type="submit" onClick={() => onClickSearch(post)}/>
            </form>
        </div>
        <div>
            photo
        </div>
    </div>
  );
};

export default SavePostComponent;
