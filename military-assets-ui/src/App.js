import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Purchase from './pages/Purchase';
import Transfer from './pages/Transfer';
import Assignment from './pages/Assignment';
import Expenditure from './pages/Expenditure';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('username'));

  const handleLogin = ({ username, password }) => {
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);
    setIsLoggedIn(true);
  };

  return (
    <Router>
      {isLoggedIn && <Navbar />}
      <Routes>
        <Route path="/login" element={!isLoggedIn ? <Login onLogin={handleLogin} /> : <Navigate to="/dashboard" />} />
        <Route path="/dashboard" element={isLoggedIn ? <Dashboard /> : <Navigate to="/login" />} />
        <Route path="/purchase" element={isLoggedIn ? <Purchase /> : <Navigate to="/login" />} />
        <Route path="/transfer" element={isLoggedIn ? <Transfer /> : <Navigate to="/login" />} />
        <Route path="/assignment" element={isLoggedIn ? <Assignment /> : <Navigate to="/login" />} />
        <Route path="/expenditure" element={isLoggedIn ? <Expenditure /> : <Navigate to="/login" />} />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  );
}

export default App;
