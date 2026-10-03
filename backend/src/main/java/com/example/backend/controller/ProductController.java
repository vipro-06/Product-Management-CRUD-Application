package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Product;
import com.example.backend.service.ProductService;

/**
 * REST Controller responsible for handling Product API requests.
 * It receives HTTP requests and communicates with the ProductService.
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Spring automatically provides ProductService here.
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        Product createdProduct = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // READ - Get all products
    // GET /api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    // READ - Get one product
    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    // UPDATE
    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        Product updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE
    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        boolean deleted = productService.deleteProduct(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}