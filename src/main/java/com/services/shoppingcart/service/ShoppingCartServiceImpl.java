package com.services.shoppingcart.service;

import com.services.shoppingcart.exception.ProductNotFoundException;
import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Shopping Cart Service Implementation.
 *
 * Created by Nilesh on 30/01/2018.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    /**
     * Creates Shopping Cart from List of Product Names.
     *
     * @param productNameList the List of product names.
     * @return created Shopping Cart
     * @throws ProductNotFoundException if Product details can not be retrieved from product name.
     * @throws ShoppingCartException if there was a problem in creating shopping cart.
     */
    public ShoppingCart createShoppingCart(List<String> productNameList) throws ProductNotFoundException, ShoppingCartException {
        throw new ProductNotFoundException("Product [xyz] Not Found");
    }
}
