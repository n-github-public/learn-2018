package com.services.shoppingcart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Shopping Cart.
 *
 * Created by Nilesh on 30/01/2018.
 */
public class ShoppingCart {

    /* The Shopping Card Id. */
    private String cartId;

    /** The list of Shopping Cart Items */
    private List<ShoppingCartItem> cartItemList = new ArrayList<>();

    /** Total Cart Price **/
    private BigDecimal totalCartPrice;

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<ShoppingCartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<ShoppingCartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public String getCartId() {
        return cartId;
    }

    public BigDecimal getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(BigDecimal totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }
}
