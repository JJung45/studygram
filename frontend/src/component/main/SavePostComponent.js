import React, { useState, useEffect } from "react";
import PostApi from "../../lib/api/post";
import '../../styles/Write.css';

const SavePostComponent = () => {
    const [id, setId] = useState('');
    const [post, setPost] = useState({
        content : "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
    
        setPost((preNewPost) => ({
          ...preNewPost,
          [name]: value,
        }));
      };
    
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
    <div className="Write">
      <div>
          <input type='text' id="title_txt" placeholder="제목"/>
      </div>

      <div>
          <textarea id="content_txt" placeholder="내용을 입력하세요."></textarea>
      </div>

      <div>
        <div id='post_submit'>
          <button> 포스트 등록 </button>
        </div>
      </div>
    </div>
  );
};

export default SavePostComponent;
