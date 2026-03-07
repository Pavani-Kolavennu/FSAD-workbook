package com.klu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.entity.Product;
import com.klu.repo.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Product> filterByPrice(double min, double max) {
        return repo.findByPriceBetween(min, max);
    }

    public List<Product> getSorted() {
        return repo.getProductsSortedByPrice();
    }

    public List<Product> getExpensive(double price) {
        return repo.getExpensiveProducts(price);
    }
}