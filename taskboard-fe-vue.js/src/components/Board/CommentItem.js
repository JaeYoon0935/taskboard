import React from "react";

const CommentItem = ({ comment, onDelete }) => {
    return (
        <div className="comment-item">
            <p className="comment-textarea">
                <span>{comment.regUser}:</span><br/><span>{comment.content}</span>
            </p>
            <button className="comment-submit-btn" onClick={() => onDelete(comment.commentId)}>삭제</button>
        </div>
    );
};

export default CommentItem;