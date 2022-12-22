package com.dev.godoy.almox.api.services;

import com.dev.godoy.almox.api.models.Product;
import com.dev.godoy.almox.api.repositories.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findByCode(String code) {
        return repository.findByCode(code);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void update(String code, Product product) {
        repository.save(product);
    }

    public void delete(String code) {
        repository.delete(code);
    }
}
