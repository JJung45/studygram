import React, { useState, useEffect  } from 'react';
import "../../styles/modal.css";
import '../../styles/Write.css';
import PostApi from "../../lib/api/post";
import { useParams } from 'react-router-dom';

const SavePostModal = (props) => {
      // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
      const { open, close, header } = props;

      const { postId } = useParams();
      const [post, setPost] = useState({
          content : ""
      });
  
      useEffect(() => {
        PostApi.getPost(postId).then(res => {
          setPost((res) => ({
            content: res.data.content
          }))
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
        <div className={open ? 'openModal modal' : 'modal'}>
          {open ? (
            <section>
              <form onSubmit={onClickSearch} method="post" className="Write">
              <header>
                {header}
                <button className="close" onClick={close}>
                  &times;
                </button>
              </header>
              <main>
                  <textarea name="content" id="content_txt" placeholder="내용을 입력하세요."
                    onChange={handleChange} />
              </main>
              <footer>
              <button type="submit"> Post </button>
              </footer>
              </form>
            </section>
          ) : null}
        </div>
      );
    }

export default SavePostModal;