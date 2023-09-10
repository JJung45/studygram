import React, {useState, useEffect, useRef} from "react";
import "../../styles/Setting.css";
import UserApi from "../../lib/api/user";
import {useLocation} from "react-router-dom";

const ProfileEditComponent = () => {
    /**
     * '프로필 편집' 버튼 클릭 시 변경되는 화면 컴포
     * 프로필편집
     * 1. 프로필 사진, 아이디, 프로필 사진 바꾸기
     * 2. 소개
     * 3. 성별
     * 4. 공개/비공개
     * 알림
     * 활동 (나의 댓글, 좋아요, 저장한 게시물)
     * @returns {JSX.Element}
     * @constructor
     */

    const location = useLocation();
    const info = location.state?.info;
    const [imgUrl, setImgUrl] = useState(info.profileImageUrl);
    const [publicType, setPublicType] = useState(info.publicType==="Y");

    const fileInput = useRef(null);
    const saveProfileImage = (e) => {
        console.log('프로필 이미지 선택');
        if (e.target.files[0]) {
            console.log('file', e.target.files[0]);
            setImgUrl(e.target.files[0])
        } else {
            setImgUrl(info.profileImageUrl);
            return;
        }
        const reader = new FileReader();
        reader.onload = () => {
            if (reader.readyState === 2) {
                setImgUrl(reader.result);
            }
        }
        reader.readAsDataURL(e.target.files[0]);
    }

   const updateUserInfo = () => {
        // file 보낼때 무조건 form 으로 보내야하나? multipart-form 이라서?
        let formData = new FormData();
        formData.append("fileImage", imgUrl)

        UserApi.userProfileImageUpload(info.idx, formData)
            .then((res) => {
                console.log("Res", res);
            })
    }

    // userAPI
    return (
        <article className="_ab83">
            <div>
                <div>
                    <div>
                        <h2>프로필 편집</h2>
                    </div>
                </div>
            </div>
            <form onSubmit={updateUserInfo} method="patch">
                <div>
                    <div>
                        <div className="myProfile">
                            <button onClick={() => fileInput.current.click()}>
                                <img alt="프로필 사진" className="pic" src={imgUrl}/>
                                <input name="profileImage" type="file" accept="image/*" id="profileImage"
                                       onChange={saveProfileImage} style={{display: "none"}} ref={fileInput}/>
                            </button>
                        </div>
                    </div>
                    <div>
                        <div>
                            <span>{info.userName}</span>
                        </div>
                        <div onClick={() => fileInput.current.click()}>
                            프로필 사진바꾸기
                            <input name="profileImage" type="file" accept="image/*" id="profileImage"
                                   onChange={saveProfileImage} style={{display: "none"}} ref={fileInput}/>
                        </div>
                    </div>
                </div>
                <div>
                    <aside>
                        <label>소개</label>
                    </aside>
                    <div>
                        <textarea></textarea>
                        <div>
                            <span>0 / 150</span>
                        </div>
                    </div>
                </div>
                <div>
                    <aside>
                        <label>프로필 공개</label>
                    </aside>
                    <div>
                        <label>
                            <input className="_aahe" id="f2a55e2f3866c08" type="checkbox" checked={publicType}
                                   onChange={({target: {publicType}}) => setPublicType(!publicType)}/>
                        </label>
                    </div>
                </div>
                <div className="_ab3p">
                    <aside className="_ad6_ _ad71">
                        <label className="_ab3q"></label>
                    </aside>
                    <div className="_ab3t">
                        <div className="_ab47">
                            <div aria-disabled="true"
                                 className="x1i10hfl xjqpnuy xa49m3k xqeqjp1 x2hbi6w x972fbf xcfux6l x1qhh985 xm0m39n xdl72j9 x2lah0s xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r x2lwn1j xeuugli xexx8yu x18d9i69 x1hl2dhg xggy1nq x1ja2u2z x1t137rt x1q0g3np x1lku1pv x1a2a7pz x6s0dn4 xjyslct x1lq5wgf xgqcy7u x30kzoy x9jhf4c x1ejq31n xd10rxx x1sy0etr x17r0tee x9f619 x9bdzbf x1ypdohk x78zum5 x1i0vuye x1f6kntn xwhw2v2 x10w6t97 xl56j7k x17ydfre x1swvt13 x1pi30zi x1n2onr6 x2b8uid xlyipyv x87ps6o x14atkfc xcdnw81 x1tu34mt xzloghq xuzhngd x47corl"
                                 role="button" tabIndex="-1"><span className="xbyyjgo">제출</span></div>
                        </div>
                    </div>
                </div>
            </form>
        </article>
    );
}
export default ProfileEditComponent;