import React from "react";
import { Search } from "lucide-react";
import "./SearchBar.css";

function SearchBar({ value, onChange }) {
  return (
    <div className="search-container">
      <input
        type="text"
        placeholder="Buscar productos..."
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="search-input"
      />
      <Search className="search-icon" size={24} />
    </div>
  );
}

export default SearchBar;
