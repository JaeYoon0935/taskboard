import React, { useState, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import { signUpUser } from "../../utils/authService";
import { useNavigate } from "react-router-dom";

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userName, setUserName] = useState("");
  const [birth, setBirth] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const { dispatch } = useContext(AuthContext);
  const navigate = useNavigate();

  const regProc = async (e) => {
    e.preventDefault();
    try {
      const data = await signUpUser(email, password, userName, birth, phoneNumber );
      dispatch({ type: "LOGIN", payload: data });
      alert("회원가입이 완료되었습니다.");
      navigate("/");
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="signUp-container">
      <form className="signUp-form" onSubmit={regProc}>
        <div className="signUp-input-group">
          <div>
            <input type="email" placeholder="이메일" value={email} onChange={(e) => setEmail(e.target.value)} required className="signUp-input"/>
          </div>
          <div>
            <input type="password" placeholder="비밀번호" value={password} onChange={(e) => setPassword(e.target.value)} required className="signUp-input"/>
          </div>
          <br/>
          <div>
            <input type="text" placeholder="성명" value={userName} onChange={(e) => setUserName(e.target.value)} required className="signUp-input"/>
          </div>
          <div>
            <input type="text" placeholder="생년월일" value={birth} onChange={(e) => setBirth(e.target.value)} required className="signUp-input"/>
          </div>
          <div>
            <input type="text" placeholder="연락처" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} required className="signUp-input"/>
          </div>
          <div>
            <button type="submit" className="signUp-button">가입완료</button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default SignUp;
