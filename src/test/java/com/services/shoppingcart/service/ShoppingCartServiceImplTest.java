package com.services.shoppingcart.service;

import com.services.shoppingcart.config.ShoppingCartSpringConfig;
import com.services.shoppingcart.exception.ProductNotFoundException;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Shopping Cart Service Unit Tests.
 *
 * Created by nmsampat on 30/01/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingCartSpringConfig.class)
public class ShoppingCartServiceImplTest extends TestCase {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test(expected = ProductNotFoundException.class)
    public void testCreateShoppingCart() throws Exception {
        shoppingCartService.createShoppingCart(Arrays.asList("APPLE", "ORANGE", "UNKNOWN"));
        fail("Expecting ProductNotFound exception to be thrown here.");
    }
}