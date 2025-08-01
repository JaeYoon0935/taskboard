import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import CommentArea from "./CommentArea.js";

const PostsDetail = () => {
    const { postsId } = useParams();

    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [regUser, setRegUser] = useState("");
    const [regDts, setRegDts] = useState("");

    useEffect(() => {
        const getPostDetail = async () => {
            try {
                const response = await axios.post("/api/Board/postDetail", { postsId });
                setTitle(response.data.title);
                setContent(response.data.content);
                setRegUser(response.data.regUser);
                setRegDts(response.data.regDts);
            } catch (error) {
                alert("게시글 불러오기에 실패하였습니다.");
            }
        };
    
        getPostDetail();
    }, [postsId]); // postsId가 변경될 때마다 실행

    const modProc = () => {
        if (!title || !content) {
            alert("제목과 내용을 입력해주세요.");
            return;
        }

        try {
            axios.post("/api/Board/modPost", { postsId, title, content })
            .then(response => {
                if(response.status === 200){
                    alert("수정 되었습니다.");
                    window.location.href = '/Board';
                }else{
                    alert("게시글 수정에 실패하였습니다.")
                }
            }).catch(() => alert("게시글 수정에 실패하였습니다."))
        } catch (error) {
            alert("게시글 수정에 실패하였습니다.")
        }
    }

    const delProc = () => {
        if(window.confirm("게시글을 삭제하시겠습니까?")){
            axios.post("/api/Board/delPost", { postsId})
            .then(response =>{
                if(response.status == 200){
                    alert("삭제 되었습니다.");
                    window.location.href = '/Board';
                }else{
                    alert("게시글 삭제에 실패하였습니다.")
                }
            }).catch(() => alert("게시글 삭제에 실패하였습니다."))
        }
    }

    return (
        <div className="posts-container">
            <h2>게시글 상세보기</h2>
            <br/>
            <div className="posts-wrapper">
                <table className="posts-table">
                    <tbody>
                        <tr>
                            <td>제목</td>
                            <td>
                                <input value={title} onChange={(e) => setTitle(e.target.value)}/>
                            </td>
                        </tr>
                        <tr>
                            <td>작성자</td>
                            <td className="posts-readOnlyCont">
                                <span>{regUser}</span>
                            </td>
                        </tr>
                        <tr>
                            <td>등록일</td>
                            <td className="posts-readOnlyCont">
                                <span>{regDts}</span>
                            </td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
                                <textarea value={content} onChange={(e) => setContent(e.target.value)}/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div className="posts-actions">
                    <a href="#" onClick={modProc} className="register-btn">수정</a>
                    &nbsp;&nbsp;
                    <a href="#" onClick={delProc} className="register-btn">삭제</a>
                </div>
                <CommentArea postsId={postsId} /> {/* postsId는 props 이다. */}
                <div className="posts-actions">
                    <Link to="/Board" className="list-btn">목록</Link>
                </div>
            </div>
        </div>
    );
};

export default PostsDetail;
