import React from "react";

const CommentItem = ({ comment, onDelete }) => {
    return (
        <div className="comment-item">
            <textarea className="comment-textarea" value={comment.content} />
            <button className="comment-submit-btn" onClick={() => onDelete(comment.commentId)}>삭제</button>
        </div>
    );
};

export default CommentItem;