package com.lambda.Lambdaintegration.controller;


import com.lambda.Lambdaintegration.model.Product;
import com.lambda.Lambdaintegration.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {

        return service.create(product);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable String id) {

        return service.getById(id);
    }

    @GetMapping
    public List<Product> getAll() {

        return service.getAll();
    }

    @PutMapping("/{id}")
    public Product update(
            @PathVariable String id,
            @RequestBody Product product) {

        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {

        service.delete(id);

        return "Deleted Successfully";
    }
}