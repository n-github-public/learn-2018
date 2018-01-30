package com.services.shoppingcart.repository;

import com.services.shoppingcart.exception.ProductNotFoundException;
import com.services.shoppingcart.model.Product;
import org.springframework.stereotype.Repository;

/**
 * The Product Repository.
 *
 * Created by Nilesh on 30/01/2018.
 */
public interface ProductRepository {
    /**
     * Retrieves product details matching product name.
     *
     * @param productName the name of the product
     * @return The Product object
     * @throws ProductNotFoundException if the Product matching with the product name was not found
     */
    Product getProduct(String productName) throws ProductNotFoundException;
}
