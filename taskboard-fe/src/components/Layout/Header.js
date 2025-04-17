import React, { useContext } from 'react';
import { AuthContext } from "../../context/AuthContext";
import { Link, useNavigate } from "react-router-dom";

function Header() {
    const { state, dispatch } = useContext(AuthContext);
    const navigate = useNavigate();

    const logOutProc = () => {
      alert("로그아웃 되었습니다.");
      dispatch({ type: "LOGOUT"});
      navigate("/"); // 로그인 후 홈으로 이동
    }

    return (
        <header className="header">
            <div className="top-bar">
                <div className="user-logout">
                    {state.token && (<button onClick={logOutProc}>로그아웃</button>)}
                </div>
            </div>

            {/* 네비게이션 바 */}
            <div className="nav-container">
                <div className="logo">
                    <Link to="/" style={{ textDecoration: "none", color: "inherit" }}>
                        <h1>Task-board</h1>
                    </Link>
                </div>
                <nav className="menu">
                    <ul>
                        <li><Link to="/UserMng">사용자 관리</Link></li>
                        <li><Link to="/Board">게시판</Link></li>
                        <li><Link to="/TodoList">To-do-List</Link></li>
                    </ul>
                </nav>
            </div>
        </header>
    );
}

export default Header;
