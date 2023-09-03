import React, {useState, useEffect} from "react";
import userAPI from "../../lib/api/user";
import "../../styles/Setting.css";
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
const SettingsComponent = () => {
    const [info, setInfo] = useState(async () => {
        await userAPI.myInfo().then((res) => {
            setInfo(res.data.body.user);
        });
    });

    // userAPI
    return (
        <section className="section-info">
            <main className="main-info">
                <h1 className="_afws">설정</h1>
                <div className="_ab81 _ab82">
                    <ul className="_ab85">
                        <li><a>프로필 편집</a></li>
                        <li><a>푸시 알림</a></li>
                        <li><a>활동</a></li>
                    </ul>
                    <article className="_ab83">
                        <div>
                            <div>
                                <div>
                                    <h2>프로필 편집</h2>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div>
                                <div>
                                    <button>
                                        <img alt="프로필 사진 추가"/>
                                    </button>
                                </div>
                            </div>
                            <div>
                                <div>
                                    <span>{info.userName}</span>
                                </div>
                                <div>
                                    프로필 사진 바꾸기
                                </div>
                            </div>
                        </div>
                        <form>
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
                                    <div>
                                        <input className="_aahe" id="f2a55e2f3866c08" type="checkbox" checked=""/>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </article>
                </div>
            </main>
        </section>

    );
}
export default SettingsComponent;