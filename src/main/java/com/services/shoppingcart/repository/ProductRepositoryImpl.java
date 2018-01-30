package com.services.shoppingcart.repository;

import com.services.shoppingcart.exception.ProductNotFoundException;
import com.services.shoppingcart.model.Product;
import org.springframework.stereotype.Component;

/**
 * The skeleton implementation.
 * For this excercise it is not required as unit test will mock this repository.
 *
 * TODO: This will need to be implemented.
 * Created by Nilesh on 30/01/2018.
 */
@Component
public class ProductRepositoryImpl implements ProductRepository {
    /**
     * Retrieves product details matching product name.
     *
     * @param productName the name of the product
     * @return The Product object
     * @throws ProductNotFoundException if the Product matching with the product name was not found
     */
    public Product getProduct(String productName) throws ProductNotFoundException {
        return null;
    }
}
