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

    private List<ShoppingCartItem> cartItemList = new ArrayList<>();

    private BigDecimal totalCartPrice;

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setCartItemList(List<ShoppingCartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void setTotalCartPrice(BigDecimal totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }
}
