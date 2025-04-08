import React, { createContext, useReducer, useEffect } from "react";

const initialState = {
  userName: localStorage.getItem("userName") || null,
  email: localStorage.getItem("email") || null,
  token: localStorage.getItem("token") || null,
};

const authReducer = (state, action) => {
  switch (action.type) {
    case "LOGIN":
      return { ...state, userName: action.payload.userName, email: action.payload.email, token: action.payload.token };
    case "LOGOUT":
      return { ...state, userName: null, email: null, token: null };
    default:
      return state;
  }
};

export const AuthContext = createContext(); // Context 생성 (context API)

export const AuthProvider = ({ children }) => { // children은 App.js의 <AuthProvider>의 'div 태그' 의 자식요소..

  const [state, dispatch] = useReducer(authReducer, initialState);

  useEffect(() => {
    if (state.token) {
      localStorage.setItem("userName", state.userName);
      localStorage.setItem("email", state.email);
      localStorage.setItem("token", state.token);
    } else {
      localStorage.removeItem("userName");
      localStorage.removeItem("email");
      localStorage.removeItem("token");
    }
  }, [state.userName, state.email, state.token]);

  // context api를 통해 하위 컴포넌트로 state와 dispatch 전달
  return (
    <AuthContext.Provider value={{ state, dispatch }}>
      {children}
    </AuthContext.Provider>
  );

};