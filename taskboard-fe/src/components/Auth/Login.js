import React, { useState, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import { loginUser } from "../../utils/authService";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { dispatch } = useContext(AuthContext);
  const navigate = useNavigate();

  const loginProc = async (e) => {
    e.preventDefault(); //form 페이지 새로고침 방지
    try {
      const data = await loginUser(email, password);
      dispatch({ type: "LOGIN", payload: data });
      navigate("/"); // 로그인 후 홈으로 이동
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={loginProc}>
        <div className="login-input-group">
          <div>
            <input type="email" placeholder="이메일" value={email} onChange={(e) => setEmail(e.target.value)} required className="login-input"/>
          </div>
          <div>
            <input type="password" placeholder="비밀번호" value={password} onChange={(e) => setPassword(e.target.value)} required className="login-input"/>
          </div>
          <div>
            <button type="submit" className="login-button">로그인</button>
          </div>
          <div>
            <Link to="/signUp">
              <button type="button" className="signUp-button">회원가입</button>
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
};

export default Login;