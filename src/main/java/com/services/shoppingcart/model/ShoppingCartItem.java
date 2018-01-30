package com.services.shoppingcart.model;

import java.math.BigDecimal;

/**
 * The Shopping Cart Item.
 *
 * Created by Nilesh on 30/01/2018.
 */
public class ShoppingCartItem {

    /* The Shopping Cart. */
    private ShoppingCart shoppingCart;

    /* The Shopping Cart Item Id. */
    private String shoppingCartItemId;

    /* The Product. */
    private Product product;

    /* The Quantity */
    private int quantity;

    /* The Shopping Cart Item Price */
    private BigDecimal shoppingCartPrice;

    public ShoppingCartItem(ShoppingCart shoppingCart, String shoppingCartItemId, Product product, int quantity, BigDecimal shoppingCartPrice) {
        this.shoppingCart = shoppingCart;
        this.shoppingCartItemId = shoppingCartItemId;
        this.product = product;
        this.quantity = quantity;
        this.shoppingCartPrice = shoppingCartPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getShoppingCartPrice() {
        return shoppingCartPrice;
    }
}
