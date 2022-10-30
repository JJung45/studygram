import React, { useState, useEffect } from "react";
import PostApi from "../../lib/api/post";
import '../../styles/Write.css';
import { useParams } from 'react-router-dom';


const SavePostComponent = () => {
    const { postId } = useParams();
    const [post, setPost] = useState({
        content : ""
    });

    useEffect(() => {
      PostApi.getPost(postId).then(res => {
        setPost(res.data);
      })
    });

    const handleChange = (e) => {
        const { name, value } = e.target;

        setPost((preNewPost) => ({
          ...preNewPost,
          [name]: value,
        }));
      };

    const onClickSearch = (e) => {
      e.preventDefault();
      addPost(post);      
    };
    
    const addPost = async(post) => {
        await PostApi.addPost({
          content: post.content
        })
        .then(() => {
            document.location.href = '/post'
        })
        .catch((err) => {
          console.log("Add post Error!", err);
        });
    
      };


  return (
    <form onSubmit={onClickSearch} method="post" className="Write">
      <div>
          <textarea name="content" id="content_txt" placeholder="내용을 입력하세요."
          onChange={handleChange} value={post.content} />
      </div>
      <div>
        <div id='post_submit'>
          <button type="submit"> 포스트 등록 </button>
        </div>
      </div>
    </form>
  );
};

export default SavePostComponent;
