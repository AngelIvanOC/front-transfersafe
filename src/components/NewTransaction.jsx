import React, { useState } from "react";
import { Upload } from "lucide-react";
import "../styles/Dashboard.css";

function NewTransaction() {
  const [formData, setFormData] = useState({
    user: "",
    price: "",
    date: "",
    file: null,
    description: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setFormData((prev) => ({
      ...prev,
      file: file,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Datos del formulario:", formData);
    // Aquí irá la lógica para enviar la transacción
  };

  return (
    <div className="transaction-card">
      <div className="transaction-header">
        <h2 className="transaction-title">Nueva Transacción</h2>
        <p className="transaction-subtitle">
          Completa los datos para crear una nueva transacción
        </p>
      </div>

      <form onSubmit={handleSubmit} className="transaction-form">
        <div className="form-row">
          <div className="form-group-dash">
            <label htmlFor="user" className="form-label-dash">
              Usuario
            </label>
            <input
              type="text"
              id="user"
              name="user"
              value={formData.user}
              onChange={handleChange}
              className="form-input-dash"
              placeholder="#usuario123"
              required
            />
          </div>

          <div className="form-group-dash">
            <label htmlFor="price" className="form-label-dash">
              Monto
            </label>
            <div className="price-input-wrapper">
              <span className="currency-symbol">$</span>
              <input
                type="number"
                id="price"
                name="price"
                value={formData.price}
                onChange={handleChange}
                className="form-input-dash price-input-field"
                placeholder="0.00"
                step="0.01"
                required
              />
            </div>
          </div>
        </div>

        <div className="form-group-dash">
          <label htmlFor="date" className="form-label-dash">
            Fecha
          </label>
          <input
            type="date"
            id="date"
            name="date"
            value={formData.date}
            onChange={handleChange}
            className="form-input-dash"
            required
          />
        </div>

        <div className="form-group-dash">
          <label htmlFor="file" className="form-label-dash">
            Archivo adjunto
          </label>
          <div className="file-input-wrapper">
            <input
              type="file"
              id="file"
              name="file"
              onChange={handleFileChange}
              className="file-input-hidden"
              accept=".pdf,.jpg,.jpeg,.png"
            />
            <label htmlFor="file" className="file-input-label">
              <Upload size={20} />
              <span>
                {formData.file
                  ? formData.file.name
                  : "Seleccionar archivo (PDF, JPG, PNG)"}
              </span>
            </label>
          </div>
        </div>

        <div className="form-group-dash">
          <label htmlFor="description" className="form-label-dash">
            Descripción
          </label>
          <textarea
            id="description"
            name="description"
            value={formData.description}
            onChange={handleChange}
            className="form-textarea-dash"
            placeholder="Describe los detalles de la transacción..."
            rows="4"
            required
          />
        </div>

        <button type="submit" className="transaction-submit-btn">
          Continuar
        </button>
      </form>
    </div>
  );
}

export default NewTransaction;
