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

    /* The Shopping Cart Item Discount Amount */
    private BigDecimal shoppingCartItemDiscountAmount;

    /* The Shopping Cart Item Price */
    private BigDecimal shoppingCartItemPrice;

    public ShoppingCartItem(ShoppingCart shoppingCart, String shoppingCartItemId, Product product, int quantity, BigDecimal shoppingCartItemPrice) {
        this.shoppingCart = shoppingCart;
        this.shoppingCartItemId = shoppingCartItemId;
        this.product = product;
        this.quantity = quantity;
        this.shoppingCartItemPrice = shoppingCartItemPrice;
    }

    public ShoppingCartItem(ShoppingCart shoppingCart, String shoppingCartItemId, Product product, int quantity, BigDecimal shoppingCartItemDiscountAmount, BigDecimal shoppingCartItemPrice) {
        this.shoppingCart = shoppingCart;
        this.shoppingCartItemId = shoppingCartItemId;
        this.product = product;
        this.quantity = quantity;
        this.shoppingCartItemDiscountAmount = shoppingCartItemDiscountAmount;
        this.shoppingCartItemPrice = shoppingCartItemPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getShoppingCartItemPrice() {
        return shoppingCartItemPrice;
    }

    public BigDecimal getShoppingCartItemDiscountAmount() {
        return shoppingCartItemDiscountAmount;
    }

    public void setShoppingCartItemDiscountAmount(BigDecimal shoppingCartItemDiscountAmount) {
        this.shoppingCartItemDiscountAmount = shoppingCartItemDiscountAmount;
    }

    public void setShoppingCartItemPrice(BigDecimal shoppingCartItemPrice) {
        this.shoppingCartItemPrice = shoppingCartItemPrice;
    }
}
