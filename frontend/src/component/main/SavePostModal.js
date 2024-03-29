import React, { useState, useEffect  } from 'react';
import "../../styles/modal.css";
import '../../styles/Write.css';
import PostApi from "../../lib/api/post";
import UserApi from "../../lib/api/user";
import { useParams } from 'react-router-dom';
import { useLocation } from "react-router-dom";

const SavePostModal = (props) => {
      // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
      const { open, close, header } = props;
      const { postId } = useParams();
      const [post, setPost] = useState({
          content : ""
      });
      const [fileImage, setFileImage] = useState("");
      const [imgBase64, setImgBase64] = useState("");

      const pathname = useLocation().pathname;
      const [user, setUser] = useState(async () => {
        await UserApi.myInfo().then((res) => {
          setUser(res.data.body.user);
        });
      });
      
      const saveFileImage = (event: any) => {
        setFileImage(event.target.files[0]);
        let reader = new FileReader();
        reader.readAsDataURL(event.target.files[0]);
        reader.onload = () => {
            setImgBase64(reader.result);
        }
      }
  
      const handleChange = (e) => {
          const { name, value } = e.target;

          setPost((preNewPost) => ({
            ...preNewPost,
            [name]: value,
          }));
        };
  
      const onClickWrite = (e) => {
        e.preventDefault();
        addPost(post);      
      };

      const captureReject = (e) => {
        // 이벤트 버블링 방지
        e.preventDefault();
      };
      
      const addPost = async(post) => {
          let formData = new FormData();

          formData.append("fileImage", fileImage);
          formData.append("content", post.content);

          await PostApi.addPost(formData)
          .then(() => {
              document.location.href = '/post'
          })
          // .catch(error => {
          //   // Promise에서 발생한 에러를 처리합니다.
          //   console.error(error);
          //   // 에러 스크린을 구성하거나 다른 작업을 수행할 수 있습니다.
          //   // 예를 들어, 상태를 업데이트하여 에러 메시지를 보여줄 수 있습니다.
          // });
      
      };  
  
      return (
        <div className={open ? 'openModal modal savepost' : 'modal savepost'}>
          {open ? (
            <section>
              <form onSubmit={onClickWrite} method="post" className="Write"  encType="multipart/form-data">
              <header>
                {header}
                <button className="close" onClick={close}>
                  &times;
                </button>
              </header>
              <main>
                  <div className="file">
                    <div className="imageSelect" style={{ alignItems: "center", justifyContent: "center", height: "100%"}} >
                      <input
                          name="fileImage"
                          type="file"
                          accept="image/*"
                          id="fileImage"
                          onChange={saveFileImage} multiple />
                      {imgBase64 && 
                      // (<div style={{ backgroundImage: "url("+imgBase64+")", backgroundRepeat: "no-repeat", backgroundSize : "cover", height : "100%"}}></div>)}
                      (<div style={{ background: `url(${imgBase64}) no-repeat center center / contain`, width:"100%", height : "100%"}}></div>)}
                    </div>
                  </div>
                  <div className="postContent">
                    <div className="myProfile">
                        <img src={user.profileImageUrl} alt="" />
                        <div>
                          <h1 className="profile-user-name">{user.userName}</h1>
                        </div>
                    </div>
                    <textarea name="content" id="contentTxt" placeholder="내용을 입력하세요."
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