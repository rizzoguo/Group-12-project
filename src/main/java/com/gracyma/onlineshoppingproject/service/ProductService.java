package com.gracyma.onlineshoppingproject.service;


import com.gracyma.onlineshoppingproject.model.OnlineShoppingProducts;
import com.gracyma.onlineshoppingproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<OnlineShoppingProducts> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<OnlineShoppingProducts> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public OnlineShoppingProducts createProduct(OnlineShoppingProducts product) {
        return productRepository.save(product);
    }

    public OnlineShoppingProducts updateProduct(Long id, OnlineShoppingProducts updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}