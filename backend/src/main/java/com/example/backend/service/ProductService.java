package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.Product;
import com.example.backend.repository.ProductRepository;

/**
 * Service layer containing the business logic for Product operations. It
 * connects the Controller with the ProductRepository.
 */

@Service
public class ProductService {

	private final ProductRepository productRepository;

	// Spring automatically provides ProductRepository here.
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// CREATE
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	// READ - Get all products
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// READ - Get one product by ID
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	// UPDATE
	public Product updateProduct(Long id, Product product) {

		Product existingProduct = productRepository.findById(id).orElse(null);

		// If product doesn't exist, return null.
		if (existingProduct == null) {
			return null;
		}

		// Update the existing product's values.
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setDescription(product.getDescription());

		return productRepository.save(existingProduct);
	}

	// DELETE
	public boolean deleteProduct(Long id) {

		// Check whether the product exists first.
		if (!productRepository.existsById(id)) {
			return false;
		}

		productRepository.deleteById(id);
		return true;
	}
}