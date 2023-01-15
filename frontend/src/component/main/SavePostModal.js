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
      const [fileImage, setFileImage] = useState("");
      
      const saveFileImage = (event: React.ChangeEvent<HTMLInputElement>) =>{
        setFileImage(URL.createObjectURL(event.target.files[0]));
      };

      const deleteFileImage = () =>{
        URL.revokeObjectURL(fileImage);
        setFileImage("");
      };
  
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

          const share = JSON.stringify({
            content: post.content,
            fileImage : fileImage
          });

          await PostApi.addPost(share)
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
              <form onSubmit={onClickSearch} method="post" className="Write"  enctype="multipart/form-data">
              <header>
                {header}
                <button className="close" onClick={close}>
                  &times;
                </button>
              </header>
              <main>
                  <div className="file">
                    <div className="imageSelect" style={
                      fileImage ? { position: "absolute",width: "33%", height: "66% "} : {alignItems: "center",
                      justifyContent: "center", height: "100%"}} >
                      <input
                          name="fileImage"
                          type="file"
                          accept="image/*"
                          onChange={saveFileImage} multiple/>
                    </div>
                    <div style={{ height: "100%", paddingTop: "0"}}>
                      {fileImage && 
                      (<div style={{ backgroundImage: "url("+fileImage+")", backgroundRepeat: "no-repeat", backgroundSize : "cover", height : "100%"}}>
                                         
                                        </div>)                                            
                          }
                    </div>
                  </div>
                  <div className="post_content">
                    <div className="myProfile">
                        <img className="pic" src="https://cdn4.iconfinder.com/data/icons/48-bubbles/48/30.User-512.png" alt="minchoi 프로필 사진" />
                        <div>
                          <span className="userID point-span">minchoi</span>
                        </div>
                    </div>
                    <textarea name="content" id="content_txt" placeholder="내용을 입력하세요."
                      onChange={handleChange} />
                  </div>
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