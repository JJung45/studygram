import React, { Component } from 'react';
import CommentApi from "../../lib/api/Comment";

const CommentComponent =() => {

}
class CommentListComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            comments: [],
            message: null
        }
    }

    componentDidMount() {
        this.reloadCommentList();
    }

    reloadCommentList = () => {
        // fetch("/comments?postId="+12)
        // .then(reponse => Response.json())
        // .then(data => {
        //     this.setState({
        //         comments: data
        //     })
        // })
        CommentApi.getComments(12)
        .then(res => {
            this.setState({
                comments: res.data
            })
        })
        .catch(err => {
            console.log('reloadCommentList() Error!', err);
        })
    }

    deleteComment = (commnetId) => {
        CommentApi.deleteComment(commnetId)
        .then(res => {
            this.setState({
                message: 'Comment Deleted Successfully.'
            });
        })
        .catch(err => {
            console.log('deleteComment() Error!', err);
        })
    }

    editComment = () => {
        window.localStorage.removeItem("commentID");
        this.props.history.push('/add-comment');
    }

    render() {
        return (
            <ul className="comment">
                {this.state.comments.map(comment =>
                    <li key={comment.idx} className="commentText">
                        <div className="commentMargin">
                            <span className="commentNameBold>">{comment.userId}</span>
                            {comment.content}
                        </div>
                        <div className="commentStart">
                            <i className="far fa-trash-alt" onClick={() => this.de(comment.idx)}/>
                            <i className="fas fa-heart colorHear" />
                        </div>
                    </li>
                )}
            </ul>
        )
    }
}

export default CommentComponent;