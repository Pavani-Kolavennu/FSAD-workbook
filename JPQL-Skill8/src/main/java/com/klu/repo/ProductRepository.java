package com.klu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klu.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived query methods
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL Queries

    // Sort by price
    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> getProductsSortedByPrice();

    // Products above given price
    @Query("SELECT p FROM Product p WHERE p.price > ?1")
    List<Product> getExpensiveProducts(double price);

    // Products by category (JPQL)
    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> getProductsByCategory(String category);
}