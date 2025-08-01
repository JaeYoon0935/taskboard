import React, { useState, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import axios from "axios";

const PostsReg = () => {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const { state } = useContext(AuthContext);
    const { email } = state;
    const { userName } = state;

    const regProc = async () => {
        if (!title || !content) {
            alert("제목과 내용을 입력해주세요.");
            return;
        }

        try {
            const response = await axios.post("/api/Board/regPost", { title, content, email, regUser:userName });
            if (response.status === 200) {
                alert("등록 되었습니다.");
                window.location.href = '/Board';
            }
        } catch (error) {
            alert("등록 실패하였습니다.");
        }
    };

    return (
        <div className="board-container">
            <h2>게시글 등록</h2>
            <br />
            <div className="board-wrapper">
                <table className="board-table">
                    <tbody>
                        <tr>
                            <td>제목</td>
                            <td>
                                <input value={title} onChange={(e) => setTitle(e.target.value)}/>
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
                <div className="board-actions">
                    <a href="#" onClick={regProc} className="register-btn">등록</a>
                </div>
            </div>
        </div>
    );
};

export default PostsReg;
