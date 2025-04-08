import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from "axios";
import './Board.css';

const Board = () => {
    const [postsList, setPostsList] = useState([]);

    useEffect(() => {
        axios.get('/api/Board/getPostsList')
            .then(response => {
                try {
                    setTimeout(() => {
                        setPostsList(response.data);
                    }, 300);
                } catch (error) {
                    alert("작업 중 오류가 발생하였습니다.");
                }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                alert('데이터 불러오기 실패');
            });
    }, []);

    return (
        <div className="board-container">
            <h2>게시판</h2>
            <br />
            <div className="board-wrapper">
                <table className="board-table">
                    <tbody>
                        <tr>
                            <td>글 번호</td>
                            <td>제목</td>
                            <td>등록자</td>
                            <td>등록일</td>
                            <td>수정일</td>
                        </tr>
                        {
                            postsList.map((data) => (
                                <tr key={data.postsId}>
                                    <td>{data.postsId}</td>
                                    <td><Link to={`/postsDetail/${data.postsId}`}>{data.title}</Link></td>
                                    <td>{data.regUser}</td>
                                    <td>{data.regDts}</td>
                                    <td>{data.modDts ? data.modDts : '-'}</td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>

                <div className="board-actions">
                    <Link to={'/PostsReg'} className="register-btn">등록</Link>
                </div>
            </div>
        </div>
    );
};

export default Board;


/*

// showPostsList 부분 차이 비교해보기.

import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from "axios";
import './Board.css';

const Board = () => {
    const [postsList, setPostsList] = useState([]); // postsList 상태 관리

    useEffect(() => {
        axios.get('/api/Board/getPostsList')
            .then(response => {
                try {
                    setTimeout(() => {
                        setPostsList(showPostsList(response.data)); // 상태 업데이트
                    }, 300);
                } catch (error) {
                    alert("작업 중 오류가 발생하였습니다.");
                }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                alert('데이터 불러오기 실패');
            });
    }, []); // 빈 배열([])을 넣으면 componentDidMount처럼 최초 한 번만 실행됨

    // 게시글 리스트 변환 함수
    const showPostsList = (posts) => {
        return posts.map((data) => (
            <tr key={data.postsId}>
                <td>{data.postsId}</td>
                <td><Link to={`/postsDetail/${data.postsId}`}>{data.title}</Link></td>
                <td>{data.regUser}</td>
                <td>{data.regDts}</td>
                <td>{data.modDts ? data.modDts : '-'}</td>
            </tr>
        ));
    };

    return (
        <div className="board-container">
            <h2>게시판</h2>
            <br />
            <div className="board-wrapper">
                <table className="board-table">
                    <tbody>
                        <tr>
                            <td>글 번호</td>
                            <td>제목</td>
                            <td>등록자</td>
                            <td>등록일</td>
                            <td>수정일</td>
                        </tr>
                        {postsList}
                    </tbody>
                </table>

                <div className="board-actions">
                    <Link to={'/PostsReg'} className="register-btn">등록</Link>
                </div>
            </div>
        </div>
    );
};

export default Board;

*/