package com.services.shoppingcart.service.discount.cor;

import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.ShoppingCartItem;

import java.math.BigDecimal;

/**
 * The abstract class of Shopping Cart Discount COR.
 *
 * Created by Nilesh on 30/01/2018.
 */
public abstract class ShoppingCartItemDiscount {
    /** next object in chain of responsibility */
    protected ShoppingCartItemDiscount next;

     /**
     * Sets teh next object in chain of responsibility
     * @param shoppingCartItemDiscount the next ShoppingCartItemDiscount object
     * @return next Shopping Cart Item Discount object
     */
    public ShoppingCartItemDiscount setNext(ShoppingCartItemDiscount shoppingCartItemDiscount){
        next = shoppingCartItemDiscount;
        return this;
    }

    /**
     * abstract mmethod to apply Discount to Shopping Cart item.
     * @param shoppingCartItem the shopping cart item
     * @return Discount amount for the Shopping Cart Item.
     */
    public abstract BigDecimal calculateDiscount(ShoppingCartItem shoppingCartItem) throws ShoppingCartException;

}
