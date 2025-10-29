import React from "react";
import "./ProductCard.css";

function ProductCard({ product }) {
  return (
    <div className="product-card">
      <div className="product-image">
        <div className="image-overlay"></div>
        <img src={product.image} alt={product.name} />
      </div>
      <div className="product-info">
        <div className="product-header">
          <h3 className="product-name">{product.name}</h3>
          <span className="product-category">{product.category}</span>
        </div>
        <p className="product-description">{product.description}</p>
        <div className="product-footer">
          <span className="product-price">${product.price.toFixed(2)}</span>
          <button className="buy-btn">Comprar</button>
        </div>
      </div>
    </div>
  );
}

export default ProductCard;
