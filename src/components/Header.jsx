import React from "react";
import { useNavigate } from "react-router-dom";

function Header() {
  const navigate = useNavigate();

  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleLoginClick = () => {
    navigate("/login");
  };

  return (
    <header className="header">
      <nav className="nav">
        <div className="nav-left">
          <div className="logo" onClick={scrollToTop}>
            <img
              src="/src/public/Logo.png"
              alt="TransferSafe"
              className="logo-img"
            />
          </div>
          <div className="nav-links">
            <a href="#" onClick={scrollToTop} className="nav-link">
              Home
            </a>
            <a href="#store" className="nav-link">
              Store
            </a>
          </div>
        </div>
        <button className="login-btn" onClick={handleLoginClick}>
          <span>Login</span>
        </button>
      </nav>
    </header>
  );
}

export default Header;
