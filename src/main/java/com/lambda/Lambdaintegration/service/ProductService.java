package com.lambda.Lambdaintegration.service;


import com.lambda.Lambdaintegration.model.Product;
import com.lambda.Lambdaintegration.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {

        product.setId(UUID.randomUUID().toString());

        return repository.save(product);
    }

    public Product getById(String id) {
        return repository.findById(id);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product update(String id, Product product) {

        product.setId(id);

        return repository.save(product);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}