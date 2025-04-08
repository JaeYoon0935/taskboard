import React, { useEffect, useState } from 'react';
import { Route, Routes } from "react-router-dom";

import './css/main.css';
import Header from './components/Layout/Header';
import Footer from './components/Layout/Footer';

// 게시판
import Board from './components/Board/Board';
import PostsReg from './components/Board/PostsReg';
import PostsDetail from './components/Board/PostsDetail';

import { AuthProvider } from "./context/AuthContext";
import Login from "./components/Auth/Login";
import SignUp from "./components/Auth/SignUp";
import PrivateRoute from "./components/PrivateRoute";
import { setupAxiosInterceptors } from "./utils/axiosInterceptors";

const App = () => {
  const [isInterceptorReady, setInterceptorReady] = useState(false); // 인터셉터가 설정되었는지 여부

  // useEffect는 비동기적으로 실행되므로, 인터셉터 세팅이 완료된 후에만 API 요청이 실행되도록 Promise를 사용함.
  useEffect(() => { 
    setupAxiosInterceptors().then(() => {
      setInterceptorReady(true);
    });
  }, []);
  
  if (!isInterceptorReady) {
    return <div>로딩 중...</div>; // 인터셉터 설정이 끝날 때까지 로딩 상태 유지
  }

  return (
    <AuthProvider>
      <div className="App">
        <Header/>
        <Routes>
          {/* 로그인 없이 접근 가능한 페이지 */}
          <Route path="/login" element={<Login/>} />
          <Route path="/signUp" element={<SignUp/>} />

          {/* 보호된 페이지 (로그인 필요) */}
          <Route element={<PrivateRoute/>}>
            <Route path="/" element={<Board/>} />
            <Route path="/Board" element={<Board/>} />
            <Route path="/PostsReg" element={<PostsReg/>} />
            <Route path="/PostsDetail/:postsId" element={<PostsDetail/>} />
          </Route>
        </Routes>
        <Footer/>
      </div>
    </AuthProvider>
  );
};

export default App;
