package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Product;

/**
 * Repository responsible for database operations related to Product. Spring
 * Data JPA provides CRUD methods automatically through JpaRepository.
 */

public interface ProductRepository extends JpaRepository<Product, Long> {

}