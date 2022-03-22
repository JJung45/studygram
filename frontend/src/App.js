import React, { useEffect } from 'react';
import './styles/App.css';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import { Route, Link, Routes } from 'react-router-dom';
import ForgetPasswordPage from './pages/ForgetPasswordPage';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" exact={true} element={<LoginPage/>} />
        <Route path="/register" element={<RegisterPage/>} />
        <Route path="/forgetpassword" element={<ForgetPasswordPage/>} />
      </Routes>
    </>
  )
}

export default App;
