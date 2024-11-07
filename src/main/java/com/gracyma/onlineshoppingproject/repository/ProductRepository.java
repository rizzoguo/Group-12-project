package com.gracyma.onlineshoppingproject.repository;


import com.gracyma.onlineshoppingproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom queries can be added here if needed
}
