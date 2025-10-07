import React from 'react';
import ProductCard from './ProductCard';

const ProductGrid = () => {
  // 5 Premium Sample Products for NovaStore
  const products = [
    {
      id: 1,
      name: "Nova Wireless Pro Headphones",
      category: "Audio",
      price: "$299.99",
      icon: "🎧",
      description: "Experience crystal-clear audio with our flagship wireless headphones featuring advanced noise cancellation technology.",
      features: [
        "30-hour battery life",
        "Active noise cancellation",
        "Premium leather comfort",
        "Quick charge technology"
      ]
    },
    {
      id: 2,
      name: "TechNova Smart Watch Elite",
      category: "Wearables",
      price: "$449.99",
      icon: "⌚",
      description: "Stay connected and monitor your health with our most advanced smartwatch featuring comprehensive fitness tracking.",
      features: [
        "7-day battery life",
        "Health monitoring sensors",
        "GPS tracking",
        "Water resistant"
      ]
    },
    {
      id: 3,
      name: "Nova Gaming Laptop X1",
      category: "Computing",
      price: "$1,599.99",
      icon: "💻",
      description: "Unleash your gaming potential with our high-performance laptop designed for serious gamers and content creators.",
      features: [
        "RTX 4070 Graphics",
        "32GB DDR5 RAM",
        "1TB NVMe SSD",
        "165Hz Display"
      ]
    },
    {
      id: 4,
      name: "Smart Home Hub Central",
      category: "Smart Home",
      price: "$199.99",
      icon: "🏠",
      description: "Control your entire smart home ecosystem with our intelligent hub that connects and manages all your IoT devices.",
      features: [
        "Voice control support",
        "Multi-device connectivity",
        "Advanced automation",
        "Security integration"
      ]
    },
    {
      id: 5,
      name: "Nova Bluetooth Speaker 360",
      category: "Audio",
      price: "$179.99",
      icon: "🔊",
      description: "Immerse yourself in 360-degree sound with our premium portable speaker perfect for any adventure or gathering.",
      features: [
        "360-degree sound",
        "20-hour playtime",
        "Waterproof design",
        "Wireless charging"
      ]
    }
  ];

  return (
    <section className="products-section">
      <h2 className="section-title">Featured Products</h2>
      <p className="section-subtitle">
        Discover our carefully curated collection of premium tech products designed to elevate your digital lifestyle
      </p>
      <div className="products-grid">
        {products.map(product => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </section>
  );
};

export default ProductGrid;