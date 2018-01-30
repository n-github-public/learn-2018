package com.services.shoppingcart.service;

import com.services.shoppingcart.exception.ProductNotFoundException;
import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.ShoppingCart;

import java.util.List;

/**
 * The Shopping Cart Service.
 *
 * Created by Nilesh on 30/01/2018.
 */
public interface ShoppingCartService {
    /**
     * Creates Shopping Cart from List of Product Names.
     *
     * @param productNameList the List of Product Names.
     * @return created Shopping Cart
     * @throws ProductNotFoundException if Product details can not be retrieved from product name.
     * @throws ShoppingCartException if there was a problem in creating shopping cart.
     */
    ShoppingCart createShoppingCart(List<String> productNameList) throws ProductNotFoundException, ShoppingCartException;
}
