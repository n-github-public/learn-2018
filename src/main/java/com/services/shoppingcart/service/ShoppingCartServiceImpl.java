package com.services.shoppingcart.service;

import com.services.shoppingcart.exception.ProductNotFoundException;
import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.Product;
import com.services.shoppingcart.model.ShoppingCart;
import com.services.shoppingcart.model.ShoppingCartItem;
import com.services.shoppingcart.repository.ProductRepository;
import com.services.shoppingcart.service.discount.cor.ShoppingCartItemDiscountCOR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The Shopping Cart Service Implementation.
 *
 * Created by Nilesh on 30/01/2018.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartItemDiscountCOR shoppingCartItemDiscountCOR;
    /**
     * Creates Shopping Cart from List of Product Names.
     *
     * @param productNameList the List of product names.
     * @return created Shopping Cart
     * @throws ProductNotFoundException if Product details can not be retrieved from product name.
     * @throws ShoppingCartException if there was a problem in creating shopping cart.
     */
    public ShoppingCart createShoppingCart(List<String> productNameList) throws ProductNotFoundException, ShoppingCartException {
        // TODO: apply this project wise.
        MathContext mathContext = new MathContext(2, RoundingMode.HALF_UP);

        Map<String, Integer> productQuantityMap = new HashMap<>();
        for(String productName : productNameList){
            if(productQuantityMap.containsKey(productName)){
                Integer quantity = productQuantityMap.get(productName);
                productQuantityMap.put(productName, quantity + 1);
            } else {
                productQuantityMap.put(productName, 1);
            }
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(UUID.randomUUID().toString());

        BigDecimal totalShoppingCartPrice = BigDecimal.ZERO;
        List<String> productNameKeysList = new ArrayList<>(productQuantityMap.keySet());
        for(String productName : productNameKeysList){
            Product product = productRepository.getProduct(productName);

            Integer quantity = productQuantityMap.get(productName);
//            BigDecimal shoppingCartItemPrice = product.getProductPrice().multiply(new BigDecimal(quantity), mathContext);
            BigDecimal shoppingCartItemPrice = product.getProductPrice().multiply(new BigDecimal(quantity));

            String cartItemId = UUID.randomUUID().toString();
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, cartItemId, product, quantity, shoppingCartItemPrice);

            // Apply discount
            BigDecimal discountAmount = shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
            shoppingCartItemPrice = shoppingCartItemPrice.subtract(discountAmount, mathContext);

            totalShoppingCartPrice = totalShoppingCartPrice.add(shoppingCartItemPrice);
            shoppingCartItem.setShoppingCartItemDiscountAmount(discountAmount);
            shoppingCartItem.setShoppingCartItemPrice(shoppingCartItemPrice);
            shoppingCart.getCartItemList().add(shoppingCartItem);
        }
        shoppingCart.setTotalCartPrice(totalShoppingCartPrice);
        return shoppingCart;
    }
}
