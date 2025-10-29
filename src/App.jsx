import React, { useState } from "react";
import Header from "./components/Header";
import Hero from "./components/Hero";
import SearchBar from "./components/SearchBar";
import Sidebar from "./components/Sidebar";
import ProductCard from "./components/ProductCard";
import { products } from "./data/products";
import "./App.css";

function App() {
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedCategories, setSelectedCategories] = useState([]);
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [showHero, setShowHero] = useState(true);

  const filteredProducts = products.filter((product) => {
    const matchesSearch =
      product.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      product.description.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesCategory =
      selectedCategories.length === 0 ||
      selectedCategories.includes(product.category);
    const matchesMinPrice = !minPrice || product.price >= parseFloat(minPrice);
    const matchesMaxPrice = !maxPrice || product.price <= parseFloat(maxPrice);

    return (
      matchesSearch && matchesCategory && matchesMinPrice && matchesMaxPrice
    );
  });

  return (
    <div className="app">
      <Header />

      {showHero && <Hero onExplore={() => setShowHero(false)} />}

      <section id="store" className="store-section">
        <div className="store-overlay">
          <div className="blur-circle blur-left"></div>
          <div className="blur-circle blur-right"></div>
        </div>

        <div className="store-container">
          <div className="store-content">
            <Sidebar
              selectedCategories={selectedCategories}
              onCategoryToggle={setSelectedCategories}
              minPrice={minPrice}
              maxPrice={maxPrice}
              onMinPriceChange={setMinPrice}
              onMaxPriceChange={setMaxPrice}
            />

            <main className="main-content">
              <SearchBar value={searchTerm} onChange={setSearchTerm} />

              <div className="products-grid">
                {filteredProducts.map((product) => (
                  <ProductCard key={product.id} product={product} />
                ))}
              </div>

              {filteredProducts.length === 0 && (
                <div className="no-results">
                  <p>No se encontraron productos</p>
                </div>
              )}
            </main>
          </div>
        </div>
      </section>
    </div>
  );
}

export default App;
