package com.services.shoppingcart.service;

import com.services.shoppingcart.config.ShoppingCartSpringConfig;
import com.services.shoppingcart.exception.ProductNotFoundException;
import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.Product;
import com.services.shoppingcart.model.ShoppingCart;
import com.services.shoppingcart.model.ShoppingCartItem;
import com.services.shoppingcart.repository.ProductRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Shopping Cart Service Unit Tests.
 *
 * Created by nmsampat on 30/01/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingCartSpringConfig.class)
public class ShoppingCartServiceImplTest extends TestCase {
    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private MathContext mathContext;

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FIVE = 5;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mathContext = new MathContext(2, RoundingMode.HALF_UP);
        String productApple = "APPLE";
        String productOrange = "ORANGE";
        String productUnknown = "UNKNOWN";

        when(productRepository.getProduct(productApple)).thenReturn(new Product("PRD-ID-APPLE-1", "FRUIT", "APPLE", productApple, new BigDecimal("0.60", mathContext)));
        when(productRepository.getProduct(productOrange)).thenReturn(new Product("PRD-ID-ORANGE-1", "FRUIT", "ORANGE", productOrange, new BigDecimal("0.25", mathContext)));
        when(productRepository.getProduct(productUnknown)).thenThrow(new ProductNotFoundException("Product with name [" + productUnknown + "] was not found."));
    }

    @Test(expected = ProductNotFoundException.class)
    public void testCreateShoppingCart() throws Exception {
        shoppingCartService.createShoppingCart(Arrays.asList("APPLE", "ORANGE", "UNKNOWN"));
        fail("Expecting ProductNotFound exception to be thrown here.");
    }

    @Test
    public void withEmptyListOfProductNamesCreateShoppingCartReturnsEmptyCart() throws ShoppingCartException, ProductNotFoundException {
        List<String> productNameList = new ArrayList<>();
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart((productNameList));

        assertNotNull(shoppingCart);
        assertThat(shoppingCart.getTotalCartPrice(), is(equalTo(BigDecimal.ZERO)));
        assertThat(shoppingCart.getCartItemList().size(), is(equalTo(ZERO)));
    }

    @Test
    public void withOneAppleCreateShoppingCartReturnsShoppingCartWithAppleOnlyCartItem() throws ShoppingCartException, ProductNotFoundException {
        List<String> productNameList = Arrays.asList("APPLE");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart((productNameList));

        assertNotNull(shoppingCart);
        assertThat(shoppingCart.getTotalCartPrice(), is(equalTo(new BigDecimal("0.60", mathContext))));
        assertThat(shoppingCart.getCartItemList().size(), is(equalTo(ONE)));

        ShoppingCartItem shoppingCartItem = shoppingCart.getCartItemList().get(0);
        assertThat(shoppingCartItem.getProduct().getProductName(), is(equalTo("APPLE")));
        assertThat(shoppingCartItem.getProduct().getProductPrice(), is(equalTo(new BigDecimal("0.60", mathContext))));
        assertThat(shoppingCartItem.getQuantity(), is(equalTo(ONE)));
        assertThat(shoppingCartItem.getShoppingCartPrice(), is(equalTo(new BigDecimal("0.60", mathContext))));
    }

    @Test
    public void withFiveApplesCreateShoppingCartReturnsShoppingCartWithAppleOnlyCartItem() throws ShoppingCartException, ProductNotFoundException {
        List<String> productNameList = Arrays.asList("APPLE", "APPLE", "APPLE", "APPLE", "APPLE");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart((productNameList));

        assertNotNull(shoppingCart);
        assertThat(shoppingCart.getTotalCartPrice(), is(equalTo(new BigDecimal("3.00", mathContext))));
        assertThat(shoppingCart.getCartItemList().size(), is(equalTo(ONE)));

        ShoppingCartItem shoppingCartItem = shoppingCart.getCartItemList().get(0);
        assertThat(shoppingCartItem.getProduct().getProductName(), is(equalTo("APPLE")));
        assertThat(shoppingCartItem.getProduct().getProductPrice(), is(equalTo(new BigDecimal("0.60", mathContext))));
        assertThat(shoppingCartItem.getQuantity(), is(equalTo(FIVE)));
        assertThat(shoppingCartItem.getShoppingCartPrice(), is(equalTo(new BigDecimal("3.00", mathContext))));
    }


    @Test
    public void withFiveOrangesCreateShoppingCartReturnsShoppingCartWithOrangeOnlyCartItem() throws ShoppingCartException, ProductNotFoundException {
        List<String> productNameList = Arrays.asList("ORANGE", "ORANGE", "ORANGE", "ORANGE", "ORANGE");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart((productNameList));

        assertNotNull(shoppingCart);
        assertThat(shoppingCart.getTotalCartPrice(), is(equalTo(new BigDecimal("1.25", mathContext))));
        assertThat(shoppingCart.getCartItemList().size(), is(equalTo(ONE)));

        ShoppingCartItem shoppingCartItem = shoppingCart.getCartItemList().get(0);
        assertThat(shoppingCartItem.getProduct().getProductName(), is(equalTo("ORANGE")));
        assertThat(shoppingCartItem.getProduct().getProductPrice(), is(equalTo(new BigDecimal("0.25", mathContext))));
        assertThat(shoppingCartItem.getQuantity(), is(equalTo(FIVE)));
        assertThat(shoppingCartItem.getShoppingCartPrice(), is(equalTo(new BigDecimal("1.25", mathContext))));
    }

    @Test
    public void withOneAppleAndOneOrangeCreateShoppingCartReturnsShoppingCartWithAppleAndOrangesInCartItem() throws ShoppingCartException, ProductNotFoundException {
        List<String> productNameList = Arrays.asList("APPLE", "ORANGE");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart((productNameList));

        assertNotNull(shoppingCart);
        assertThat(shoppingCart.getTotalCartPrice(), is(equalTo(new BigDecimal("0.85", mathContext))));
        assertThat(shoppingCart.getCartItemList().size(), is(equalTo(TWO)));

        ShoppingCartItem shoppingCartItem = shoppingCart.getCartItemList().get(0);
        assertThat(shoppingCartItem.getProduct().getProductName(), is(equalTo("APPLE")));
        assertThat(shoppingCartItem.getProduct().getProductPrice(), is(equalTo(new BigDecimal("0.60", mathContext))));
        assertThat(shoppingCartItem.getQuantity(), is(equalTo(ONE)));
        assertThat(shoppingCartItem.getShoppingCartPrice(), is(equalTo(new BigDecimal("0.60", mathContext))));

        shoppingCartItem = shoppingCart.getCartItemList().get(1);
        assertThat(shoppingCartItem.getProduct().getProductName(), is(equalTo("ORANGE")));
        assertThat(shoppingCartItem.getProduct().getProductPrice(), is(equalTo(new BigDecimal("0.25", mathContext))));
        assertThat(shoppingCartItem.getQuantity(), is(equalTo(ONE)));
        assertThat(shoppingCartItem.getShoppingCartPrice(), is(equalTo(new BigDecimal("0.25", mathContext))));
    }

    @Test
    public void withThreeApplesAndFiveOrangesCreateShoppingCartReturnsShoppingCartWithAppleAndOrangesInCartItem() throws ShoppingCartException, ProductNotFoundException {
        List<String> productNameList = Arrays.asList("ORANGE", "APPLE", "ORANGE", "APPLE", "APPLE", "ORANGE", "ORANGE", "ORANGE");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart((productNameList));

        assertNotNull(shoppingCart);
        assertThat(shoppingCart.getTotalCartPrice(), is(equalTo(new BigDecimal("3.05", mathContext))));
        assertThat(shoppingCart.getCartItemList().size(), is(equalTo(TWO)));

        ShoppingCartItem shoppingCartItem = shoppingCart.getCartItemList().get(0);
        assertThat(shoppingCartItem.getProduct().getProductName(), is(equalTo("APPLE")));
        assertThat(shoppingCartItem.getProduct().getProductPrice(), is(equalTo(new BigDecimal("0.60", mathContext))));
        assertThat(shoppingCartItem.getQuantity(), is(equalTo(THREE)));
        assertThat(shoppingCartItem.getShoppingCartPrice(), is(equalTo(new BigDecimal("1.80", mathContext))));

        shoppingCartItem = shoppingCart.getCartItemList().get(1);
        assertThat(shoppingCartItem.getProduct().getProductName(), is(equalTo("ORANGE")));
        assertThat(shoppingCartItem.getProduct().getProductPrice(), is(equalTo(new BigDecimal("0.25", mathContext))));
        assertThat(shoppingCartItem.getQuantity(), is(equalTo(FIVE)));
        assertThat(shoppingCartItem.getShoppingCartPrice(), is(equalTo(new BigDecimal("1.25", mathContext))));
    }


}