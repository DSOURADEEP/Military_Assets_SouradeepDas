import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <h2>Military Asset System</h2>
      <ul className="nav-links">
        <li>
          <NavLink to="/dashboard" className={({ isActive }) => (isActive ? 'active' : '')}>
            Dashboard
          </NavLink>
        </li>
        <li>
          <NavLink to="/purchase" className={({ isActive }) => (isActive ? 'active' : '')}>
            Purchase
          </NavLink>
        </li>
        <li>
          <NavLink to="/transfer" className={({ isActive }) => (isActive ? 'active' : '')}>
            Transfer
          </NavLink>
        </li>
        <li>
          <NavLink to="/assignment" className={({ isActive }) => (isActive ? 'active' : '')}>
            Assignment
          </NavLink>
        </li>
        <li>
          <NavLink to="/expenditure" className={({ isActive }) => (isActive ? 'active' : '')}>
            Expenditure
          </NavLink>
        </li>
        <li>
          <button onClick={handleLogout} className="logout-btn">
            Logout
          </button>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;

