import React from "react";
import "./Header.css";

function Header() {
  return (
    <header className="header">
      <nav className="nav">
        <div className="nav-left">
          <div className="logo">
            {/* Opción 1: Usar imagen (descomenta y ajusta la ruta) */}
            <img
              src="../assets/Logo.png"
              alt="TransferSafe"
              className="logo-img"
            />
            {/* Opción 2: Usar letra (comentar si usas imagen) */}T
          </div>
          <div className="nav-links">
            <a href="#" className="nav-link">
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
