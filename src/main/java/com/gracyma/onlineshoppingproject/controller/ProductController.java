package com.gracyma.onlineshoppingproject.controller;

import com.gracyma.onlineshoppingproject.model.Product;
import com.gracyma.onlineshoppingproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping("/products")
    public Product createProduct(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") double price,
                                 @RequestParam("image") MultipartFile image) throws IOException {
        // Define the path to save images (you may want to customize this)
        String uploadDir = "uploads/products/";
        String imageFileName = image.getOriginalFilename();
        File destinationFile = new File(uploadDir + imageFileName);

        // Save image file
        image.transferTo(destinationFile);

        // Create and save the product
        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .imageUrl(uploadDir + imageFileName) // Store the path or URL
                .build();

        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
