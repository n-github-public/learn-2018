package com.services.shoppingcart.service.discount.cor;

import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.ShoppingCartItem;

import java.math.BigDecimal;

/** The interface off Shopping Cart item Discount Chain of Responsibility Pattern.
 *
 * Created by Nilesh on 30/01/2018.
 */
public interface ShoppingCartItemDiscountCOR {
    /**
     * Calculates Discount for Shopping CartItem.
     *
     * @param shoppingCartItem the shopping Cart Item
     * @return disacount Amount.
     */
    BigDecimal calculateDiscount(ShoppingCartItem shoppingCartItem) throws ShoppingCartException;
}
