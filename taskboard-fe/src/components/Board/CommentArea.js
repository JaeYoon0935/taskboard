import React, { useState, useEffect } from "react";
import axios from "axios";
import CommentItem from "./CommentItem"; // 개별 댓글 컴포넌트

const CommentArea = ({ postsId }) => {
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");

    useEffect(() => {
        axios.post("/api/Comment/getComments", { postsId })
            .then(response => {
                setComments(response.data);
            })
            .catch(() => alert("댓글을 불러오는데 실패했습니다."));
    }, [postsId]);

    const regComment = () => {
        if (!newComment.trim()) return;
        axios.post("/api/Comment/regComment", { postsId, content: newComment })
            .then(response => {
                setComments([...comments, response.data]); // 새로운 댓글 추가
                setNewComment(""); // 입력창 초기화
            })
            .catch(() => alert("댓글 등록에 실패했습니다."));
    };

    const delComment = (commentId) => {
        if(window.confirm("댓글을 삭제하시겠습니까?")){
            axios.post("/api/Comment/delComment", { commentId })
            .then(response => {
                alert("댓글이 삭제되었습니다.");
                setComments(comments.filter(comment => comment.commentId !== commentId));
            })
            .catch(() => alert("댓글 삭제에 실패했습니다."));
        }
    };

    return (
        <div className="comment">
            <h2>Comment</h2>
            <div className="comment-actions">
                <textarea className="comment-textarea" value={newComment} onChange={(e) => setNewComment(e.target.value)}/>
                <button onClick={regComment} className="comment-submit-btn">등록</button>
            </div>
            <br/><br/>
            <div className="comment-list">
                {comments.map((comment) => (
                    <CommentItem key={comment.commentId} comment={comment} onDelete={() => delComment(comment.commentId)}/>
                ))}
            </div>
        </div>
    );
};

export default CommentArea;
