import React from 'react';
import './App.css';
import Header from './components/Header';
import ProductGrid from './components/ProductGrid';
import Footer from './components/Footer';

function App() {
  return (
    <div className="App">
      <Header />
      <main className="main-content">
        <div className="container">
          <ProductGrid />
        </div>
      </main>
      <Footer />
    </div>
  );
}

export default App;