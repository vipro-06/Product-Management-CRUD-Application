package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a Product in the application.
 * This class is mapped to the "products" table in the database using JPA.
 */

@Entity
@Table(name = "products")
public class Product {

    // Primary key of the product table.
    // The database will automatically generate this value.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Product name
    private String name;

    // Product price
    private double price;

    // Product description
    private String description;

    // Empty constructor required by JPA.
    public Product() {
    }

    // Constructor used when creating a Product object manually.
    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}