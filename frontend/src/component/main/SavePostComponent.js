import React, { useState, useEffect } from "react";
import PostApi from "../../lib/api/post";
import axios from 'axios';

const SavePostComponent = () => {
    const [id, setId] = useState('');
    const [content, setContent] = useState('');
    const [userId, setUserId] = useState(24);
    const [post, setPost] = useState({
        "content" : content,
        "userId"  : userId
    });

    
    const onClickSearch = (post) => {

        console.log(post);

        // axios({
        //     method : 'post',
        //     url : 'http://127.0.0.1:8090/post/save',
        //     headers : {
        //         'Content-Type' : 'application/json'
        //     },
        //     data : {
        //        post
        //     }
        // }).then(function(response) {
        //     console.log(response);
        // }).catch(function(error){
        //     console.log(error);
        // })
    };


  return (
    <div>
        <form name="savePost" method="post">
            <input type="hidden" name="userId" value={userId}/>
            <input name="content" onChange={(event) => setContent(event.target.value)}/>
            <input type="submit" onClick={() => onClickSearch(post)}/>
        </form>
    </div>
  );
};

export default SavePostComponent;
