import axios from "axios";

/* 로직을 서비스 계층으로 분리하여 유지보수와 확장성 향상.
   게시판과 댓글은 특정 컴포넌트 전용으로 분리하지 않음. */

export const loginUser = async (email, password) => {
  try {
    const response = await axios.post("/api/auth/login", { email, password }, {
      headers: {
        "Content-Type": "application/json"
      }
    });
    return response.data; // { user, token }
  } catch (error) {
    console.error("로그인 실패:", error);
    throw new Error("로그인 실패");
  }
};

export const signUpUser = async (email, password, userName, birth, phoneNumber) => {
  try {
    const response = await axios.post("/api/auth/signUp", 
                     { email, password, userName, birth, phoneNumber,}, {
                      headers: {
                        "Content-Type": "application/json"
                      }
                    });
    return response.data; // { user, token }
  } catch (error) {
    console.error("회원가입 실패:", error);
    throw new Error("회원가입 실패");
  }
};