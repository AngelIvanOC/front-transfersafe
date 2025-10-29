const categories = [
  "Eventos",
  "Tecnologia",
  "Viajes",
  "Entretenimiento",
  "Videojuegos",
];

function Sidebar({
  selectedCategories,
  onCategoryToggle,
  minPrice,
  maxPrice,
  onMinPriceChange,
  onMaxPriceChange,
}) {
  const toggleCategory = (category) => {
    onCategoryToggle((prev) =>
      prev.includes(category)
        ? prev.filter((c) => c !== category)
        : [...prev, category]
    );
  };

  return (
    <aside className="sidebar">
      <div className="sidebar-section">
        <h3 className="sidebar-title">Precio</h3>
        <div className="price-inputs">
          <input
            type="number"
            placeholder="Min"
            value={minPrice}
            onChange={(e) => onMinPriceChange(e.target.value)}
            className="price-input"
          />
          <input
            type="number"
            placeholder="Max"
            value={maxPrice}
            onChange={(e) => onMaxPriceChange(e.target.value)}
            className="price-input"
          />
        </div>
      </div>

      <div className="sidebar-section">
        <h3 className="sidebar-title">Categoría</h3>
        <div className="categories">
          {categories.map((category) => (
            <label key={category} className="category-label">
              <input
                type="checkbox"
                checked={selectedCategories.includes(category)}
                onChange={() => toggleCategory(category)}
                className="category-checkbox"
              />
              <span className="category-text">{category}</span>
            </label>
          ))}
        </div>
      </div>
    </aside>
  );
}

export { Sidebar };
