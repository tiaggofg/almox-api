package com.dev.godoy.almox.api.services;

import com.dev.godoy.almox.api.dtos.ProductDto;
import com.dev.godoy.almox.api.models.Product;
import com.dev.godoy.almox.api.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> findAll() {
        List<ProductDto> result = new ArrayList<>();
        for (Product product : repository.findAll()) {
            result.add(product.toDto());
        }
        return result;
    }

    public ProductDto findByCode(String code) {
        return repository.findByCode(code).toDto();
    }

    public ProductDto save(ProductDto dto) {
        Product product = repository.save(dto.toProduct());
        return product.toDto();
    }

    public void update(String code, ProductDto dto) {
        repository.update(code, dto.toProduct());
    }

    public void delete(String code) {
        repository.delete(code);
    }
}
