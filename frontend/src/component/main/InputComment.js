import React, {useState} from 'react';

function InputComment() {
    const [newComment, setNewComment] = useState('');

    const onChange = (e) => {
        setNewComment(e.target.value);
    }

    const onReset = () => {
        setNewComment('');
    }

    return (
        <input
        className="comment"
        type="text"
        placeholder="댓글 달기..."
        value={this.state.comments}
        onChange={this.getInputValue} 
        onKeyDown={this.enterComments}
      />
    )

}
