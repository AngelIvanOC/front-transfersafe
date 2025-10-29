import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Dashboard.css";

function DashboardNav({ activeSection, onSectionChange }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Aquí irá la lógica de cierre de sesión
    navigate("/");
  };

  return (
    <nav className="dashboard-nav">
      <div className="dashboard-nav-content">
        <div className="dashboard-nav-left">
          <div className="dashboard-logo">
            <img
              src="/Logo.png"
              alt="TransferSafe"
              className="dashboard-logo-img"
            />
          </div>
          <div className="dashboard-nav-links">
            <button
              className={`dashboard-nav-btn ${
                activeSection === "new" ? "active" : ""
              }`}
              onClick={() => onSectionChange("new")}
            >
              Nueva Transacción
            </button>
            <button
              className={`dashboard-nav-btn ${
                activeSection === "transactions" ? "active" : ""
              }`}
              onClick={() => onSectionChange("transactions")}
            >
              Mis Transacciones
            </button>
          </div>
        </div>
        <button className="dashboard-logout-btn" onClick={handleLogout}>
          Cerrar Sesión
        </button>
      </div>
    </nav>
  );
}

export default DashboardNav;
