package com.gracyma.onlineshoppingproject.repository;


import com.gracyma.onlineshoppingproject.model.OnlineShoppingProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<OnlineShoppingProducts, Long> {
    // Custom queries can be added here if needed
}
