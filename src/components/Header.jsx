import React from "react";

function Header() {
  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  return (
    <header className="header">
      <nav className="nav">
        <div className="nav-left">
          <div className="logo" onClick={scrollToTop}>
            <img
              src="/src/assets/Logo.png"
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
        <button className="login-btn">
          <span>Login</span>
        </button>
      </nav>
    </header>
  );
}

export default Header;
