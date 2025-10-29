import React from "react";
import "./Header.css";

function Header() {
  return (
    <header className="header">
      <nav className="nav">
        <div className="nav-left">
          <div className="logo">T</div>
          <div className="nav-links">
            <a href="#" className="nav-link">
              Home
            </a>
            <a href="#store" className="nav-link">
              Store
            </a>
          </div>
        </div>
        <button className="login-btn">Login</button>
      </nav>
    </header>
  );
}

export default Header;
