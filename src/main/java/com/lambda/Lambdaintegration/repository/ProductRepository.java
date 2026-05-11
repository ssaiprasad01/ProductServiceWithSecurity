package com.lambda.Lambdaintegration.repository;



import com.lambda.Lambdaintegration.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import software.amazon.awssdk.enhanced.dynamodb.*;

import java.util.*;

@Repository

public class ProductRepository {

    private final DynamoDbEnhancedClient enhancedClient;
    private static final String TABLE_NAME = "products";

    public ProductRepository(DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
    }

    private DynamoDbTable<Product> getTable() {

        return enhancedClient.table(
                TABLE_NAME,
                TableSchema.fromBean(Product.class)
        );
    }

    public Product save(Product product) {

        getTable().putItem(product);
        return product;
    }

    public Product findById(String id) {

        return getTable().getItem(
                Key.builder().partitionValue(id).build()
        );
    }

    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();

        getTable().scan().items().forEach(products::add);

        return products;
    }

    public void delete(String id) {

        getTable().deleteItem(
                Key.builder().partitionValue(id).build()
        );
    }
}