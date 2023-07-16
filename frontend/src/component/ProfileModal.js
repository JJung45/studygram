import React, {useEffect, useRef} from "react";
import PostComment from "./main/PostCommentComponent";
import moment from "moment/moment";
import {modalClose} from "../module/utils/utils";

/**
 * 아이디 눌렀을때 보이는 모달
 * 1. 내 프로필 사진 + 아이디
 * 2. 가입날짜
 * 3. 닫기
 * @constructor
 */
const ProfileModal = (props) => {
    const {open, close, user, type} = props;

    console.log('user',user, 'type', type);
    /**
     * INFO: 아이디 클릭
     * 1. 프로필사진, 아이디
     * 2. 가입날짜
     * 3. 닫기
     *
     * EDIT: 프로필 편집 클릭
     * 컴포넌트 전환 -> 모달 아님
     *
     * <프로필 편집>
     * 1. 프로필 사진, 아이디,
     * 2. 프로필 사진 바꾸기
     * 3. 소개
     * 4. 성별
     * 5. 공개 / 비공개 전환
     *
     * SETTING: 설정 버튼 클릭
     * 1. 활동 -> 컴포넌트 전환 <활동> 좋아요, 댓글, 저장한 게시글
     * 2. 로그아웃
     * 3. 취소
     *
     * @type {React.MutableRefObject<null>}
     */

    // 모달 영역 밖 클릭 시 닫기
    const node = useRef(null);
    const modalCloseHandler = (e) => {
        console.log('e.target', e.target);
        if(open && (!node.current.contains(e.target))) {
            close();
        }
    }
    useEffect(() => {
        if (open) {
            window.addEventListener('click', modalCloseHandler);
        } else {
            window.removeEventListener('click', modalCloseHandler);
        }
        return () => {
            window.removeEventListener('click', modalCloseHandler);
        };
    }, [open]);

    const modalContent = () => {
        if(type === "info") {
            return (
                <div>
                    <table className="table">
                        <tbody>
                            <tr>프로필 사진 / 아이디</tr>
                            <tr>가입날짜</tr>
                            <tr>닫기</tr>
                        </tbody>
                    </table>
                </div>
                );
        } else {

        }
    }



    return (
        <div className={open ? "openModal modal" : "modal"}>
            {open ? (
                <section>
                    <div ref={node}>
                        {modalContent()}
                    </div>
                </section>
            ) : null}
        </div>
    );

};
export default ProfileModal;