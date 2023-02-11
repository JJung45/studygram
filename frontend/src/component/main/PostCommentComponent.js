import React from "react";
import {Link} from "react-router-dom";
const Comment = ({data}) => {
    return (
        <div className="comments_div">
            <ul className="comments">
                <li className="comments_content" key={data.idx}>
                    <span className="point-span userID">{data.userId}</span>
                    {data.content}
                    <img
                        className="comment-heart"
                        src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png"
                        alt="하트"
                    />
                </li>
            </ul>
        </div>
    )
};

export default Comment;
