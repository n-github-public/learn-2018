package com.services.shoppingcart.service.discount.cor;

import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.ShoppingCartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * The implementation of Shopping Cart Item Discount Chain of Responsibility.
 *
 * Created by nmsampat on 30/01/2018.
 */
@Component
public class ShoppingCartItemDiscountCORImpl implements ShoppingCartItemDiscountCOR {
    /** The Root of Discount Chain of Responsibility. */
    protected ShoppingCartItemDiscount root;

    public ShoppingCartItemDiscountCORImpl() {
        // Setup the Discount Chain of Responsibility

        // Discount: Three for the Price of Two
        ShoppingCartItemDiscount three4twoDiscount = new ShoppingCartItemDiscountThreeForTwo();

        // Discount: Buy One Get One Free
        root = new ShoppingCartItemDiscountBOGOF();
        root.setNext(three4twoDiscount);
    }

    /**
     * Calculates Discount for Shopping CartItem.
     *
     * @param shoppingCartItem the shopping Cart Item
     * @return disacount Amount.
     */
    @Override
    public BigDecimal calculateDiscount(ShoppingCartItem shoppingCartItem) throws ShoppingCartException {
        return root.calculateDiscount(shoppingCartItem);
    }
}
