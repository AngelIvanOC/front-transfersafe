import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí irá la lógica de autenticación
    // Por ahora solo redirige al dashboard
    navigate("/dashboard");
  };

  return (
    <div className="login-page">
      <div className="login-background">
        <div className="login-blur-circle login-blur-1"></div>
        <div className="login-blur-circle login-blur-2"></div>
      </div>

      <div className="login-container">
        <div className="login-card">
          <div className="login-header">
            <div className="login-logo">
              <img
                src="/src/public/Logo.png"
                alt="TransferSafe"
                className="login-logo-img"
              />
            </div>
            <h1 className="login-title">Bienvenido</h1>
            <p className="login-subtitle">Inicia sesión en tu cuenta</p>
          </div>

          <form onSubmit={handleSubmit} className="login-form">
            <div className="form-group">
              <label htmlFor="email" className="form-label">
                Correo electrónico
              </label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="form-input"
                placeholder="tu@email.com"
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="password" className="form-label">
                Contraseña
              </label>
              <input
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="form-input"
                placeholder="••••••••"
                required
              />
            </div>

            <div className="form-options">
              <label className="remember-label">
                <input type="checkbox" className="remember-checkbox" />
                <span>Recordarme</span>
              </label>
              <a href="#" className="forgot-link">
                ¿Olvidaste tu contraseña?
              </a>
            </div>

            <button type="submit" className="login-submit-btn">
              Iniciar Sesión
            </button>
          </form>

          <div className="login-footer">
            <p>
              ¿No tienes una cuenta?{" "}
              <a href="#" className="signup-link">
                Regístrate
              </a>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
