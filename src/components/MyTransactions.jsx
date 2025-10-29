import React from "react";
import { FileText, Calendar, DollarSign, User } from "lucide-react";
import "../styles/Dashboard.css";

// Datos de ejemplo
const exampleTransactions = [
  {
    id: 1,
    user: "#usuario123",
    price: 150.0,
    date: "2024-10-25",
    description: "Compra de boletos para concierto",
    status: "Completada",
  },
  {
    id: 2,
    user: "#usuario456",
    price: 320.5,
    date: "2024-10-23",
    description: "Adquisición de laptop gaming",
    status: "Pendiente",
  },
  {
    id: 3,
    user: "#usuario789",
    price: 89.99,
    date: "2024-10-20",
    description: "Suscripción anual a plataforma",
    status: "Completada",
  },
  {
    id: 4,
    user: "#usuario321",
    price: 450.0,
    date: "2024-10-18",
    description: "Paquete de viaje a Cancún",
    status: "Completada",
  },
];

function MyTransactions() {
  return (
    <div className="transactions-container">
      <div className="transactions-header">
        <h2 className="transactions-title">Mis Transacciones</h2>
        <p className="transactions-subtitle">
          Historial de todas tus transacciones realizadas
        </p>
      </div>

      <div className="transactions-list">
        {exampleTransactions.map((transaction) => (
          <div key={transaction.id} className="transaction-item">
            <div className="transaction-item-header">
              <div className="transaction-status-badge">
                <span
                  className={`status-indicator ${
                    transaction.status === "Completada"
                      ? "completed"
                      : "pending"
                  }`}
                ></span>
                {transaction.status}
              </div>
              <span className="transaction-id">ID: {transaction.id}</span>
            </div>

            <div className="transaction-item-body">
              <div className="transaction-detail">
                <div className="detail-icon">
                  <User size={18} />
                </div>
                <div className="detail-content">
                  <span className="detail-label">Usuario</span>
                  <span className="detail-value">{transaction.user}</span>
                </div>
              </div>

              <div className="transaction-detail">
                <div className="detail-icon">
                  <DollarSign size={18} />
                </div>
                <div className="detail-content">
                  <span className="detail-label">Monto</span>
                  <span className="detail-value price">
                    ${transaction.price.toFixed(2)}
                  </span>
                </div>
              </div>

              <div className="transaction-detail">
                <div className="detail-icon">
                  <Calendar size={18} />
                </div>
                <div className="detail-content">
                  <span className="detail-label">Fecha</span>
                  <span className="detail-value">{transaction.date}</span>
                </div>
              </div>

              <div className="transaction-detail full-width">
                <div className="detail-icon">
                  <FileText size={18} />
                </div>
                <div className="detail-content">
                  <span className="detail-label">Descripción</span>
                  <span className="detail-value">
                    {transaction.description}
                  </span>
                </div>
              </div>
            </div>

            <div className="transaction-item-footer">
              <button className="transaction-view-btn">Ver detalles</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default MyTransactions;
