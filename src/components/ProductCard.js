import React from 'react';

const ProductCard = ({ product }) => {
  const handlePurchase = () => {
    alert(`🛒 Added "${product.name}" to cart!\n💰 Price: ${product.price}\n\nThank you for choosing TechNova Solutions!`);
  };

  return (
    <div className="product-card">
      <span className="product-category">{product.category}</span>
      <div className="product-icon">{product.icon}</div>
      <h3 className="product-name">{product.name}</h3>
      <p className="product-description">{product.description}</p>
      <div className="product-price">{product.price}</div>
      
      <ul className="product-features">
        {product.features.map((feature, index) => (
          <li key={index}>{feature}</li>
        ))}
      </ul>
      
      <button className="buy-button" onClick={handlePurchase}>
        Add to Cart
      </button>
    </div>
  );
};

export default ProductCard;