package com.services.shoppingcart.service.discount.cor;

import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.Product;
import com.services.shoppingcart.model.ProductDiscountType;
import com.services.shoppingcart.model.ShoppingCartItem;

import java.math.BigDecimal;

import static com.services.shoppingcart.model.ProductDiscountType.BOGOF;
import static com.services.shoppingcart.model.ProductDiscountType.NONE;

/**
 * Buy One Get One Free Discount Calculations.
 *
 * Created by Nilesh on 30/01/2018.
 */
public class ShoppingCartItemDiscountBOGOF extends ShoppingCartItemDiscount {

    public static final int TWO = 2;

    /**
     * abstract mmethod to apply Discount to Shopping Cart item.
     *
     * @param shoppingCartItem the shopping cart item
     * @return Discount amount for the Shopping Cart Item.
     */
    @Override
    public BigDecimal calculateDiscount(ShoppingCartItem shoppingCartItem) throws ShoppingCartException {

        if (shoppingCartItem == null){
            throw new ShoppingCartException("Missing Shopping Cart/Product information.");
        }

        if (shoppingCartItem.getProduct() == null){
            throw new ShoppingCartException("Missing Shopping Cart/Product information.");
        }

        Product product = shoppingCartItem.getProduct();
        ProductDiscountType productDiscountType = product.getProductDiscountType();
        BigDecimal productPrice = product.getProductPrice();

        BigDecimal discountAmount = BigDecimal.ZERO;
        if(productDiscountType != null && productDiscountType.equals(BOGOF)){
            int quantity = shoppingCartItem.getQuantity();
            int quantityToDiscount = quantity / TWO;

            discountAmount = productPrice.multiply(new BigDecimal(quantityToDiscount));
        } else if(next != null && productDiscountType != null && !productDiscountType.equals(NONE)){
            discountAmount = next.calculateDiscount(shoppingCartItem);
        }
        return discountAmount;
    }
}
