import React, { useState, useEffect } from "react";
import { ChevronDown } from "lucide-react";

function Hero({ onExplore }) {
  const [titleText, setTitleText] = useState("");
  const [showCursor, setShowCursor] = useState(true);
  const fullTitle = "TransferSafe";

  useEffect(() => {
    let index = 0;
    const typingTimer = setInterval(() => {
      if (index < fullTitle.length) {
        setTitleText(fullTitle.slice(0, index + 1));
        index++;
      } else {
        clearInterval(typingTimer);
        setTimeout(() => setShowCursor(true), 500);
      }
    }, 150);

    const cursorTimer = setInterval(() => {
      setShowCursor((prev) => !prev);
    }, 530);

    return () => {
      clearInterval(typingTimer);
      clearInterval(cursorTimer);
    };
  }, []);

  return (
    <section className="hero">
      <div className="hero-background">
        <div className="hero-grid">
          <svg className="grid-svg" xmlns="http://www.w3.org/2000/svg">
            <defs>
              <pattern
                id="grid"
                width="50"
                height="50"
                patternUnits="userSpaceOnUse"
              >
                <path
                  d="M 50 0 L 0 0 0 50"
                  fill="none"
                  stroke="rgba(255,255,255,0.1)"
                  strokeWidth="1"
                />
              </pattern>
            </defs>
            <rect width="100%" height="100%" fill="url(#grid)" />
          </svg>
        </div>
      </div>

      <div className="hero-content">
        <div className="hero-text-wrapper">
          <h1 className="hero-title">
            {titleText}
            {showCursor && <span className="cursor">|</span>}
          </h1>
          <p className="hero-description">
            Tu destino para boletos de eventos, tecnología de vanguardia y
            experiencias inolvidables. Descubre un nuevo mundo de posibilidades.
          </p>
        </div>
      </div>

      <button onClick={onExplore} className="scroll-btn">
        <ChevronDown size={48} />
      </button>
    </section>
  );
}

export default Hero;
