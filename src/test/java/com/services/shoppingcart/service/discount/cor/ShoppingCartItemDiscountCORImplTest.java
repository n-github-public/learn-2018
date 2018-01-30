package com.services.shoppingcart.service.discount.cor;

import com.services.shoppingcart.config.ShoppingCartSpringConfig;
import com.services.shoppingcart.exception.ShoppingCartException;
import com.services.shoppingcart.model.Product;
import com.services.shoppingcart.model.ShoppingCart;
import com.services.shoppingcart.model.ShoppingCartItem;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static com.services.shoppingcart.model.ProductDiscountType.BOGOF;
import static com.services.shoppingcart.model.ProductDiscountType.THREE_FOR_TWO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * The implementation of ShoppingCartItemDiscountCORImplTest
 *
 * Created by Nilesh on 30/01/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShoppingCartSpringConfig.class})
public class ShoppingCartItemDiscountCORImplTest extends TestCase {
    @Autowired
    private ShoppingCartItemDiscountCOR shoppingCartItemDiscountCOR;

    /** Math Context */
    private MathContext mathcontext;

    /** The Product: Apple */
    private Product productApple;

    /** The Product: Orange */
    private Product productOrange;

    @Before
    public void setUp() {
        mathcontext = new MathContext(2, RoundingMode.HALF_UP);
        productApple = new Product("Id", "cat-APPPLE-1", "APPLE", "APPLE", BOGOF, new BigDecimal("0.60"));
        productOrange = new Product("Id", "cat-ORANGE-1", "ORANGE", "ORANGE", THREE_FOR_TWO, new BigDecimal("0.25"));
    }

    // ##################### Apples #####################################
    @Test
    public void whenOnlyOneAppleInShoppingCartItemCalculateDiscountReturnsZeroDiscount() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productApple, 1, new BigDecimal("0.60", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("0.00", mathcontext))));
    }

    @Test
    public void whenTwoApplesInShoppingCartItemCalculateDiscountReturnsDiscountForOneApple() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productApple, 2, new BigDecimal("1.20", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("0.60", mathcontext))));
    }

    @Test
    public void whenFiveApplesInShoppingCartItemCalculateDiscountReturnsDiscountForTwoApples() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productApple, 5, new BigDecimal("3.00", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("1.20"))));
    }

    @Test
    public void when101ApplesInShoppingCartItemCalculateDiscountReturnsDiscountFor50Apples() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productApple, 101, new BigDecimal("60.60", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("30.00"))));
    }

    // ##################### Oranges #####################################
    @Test
    public void whenOnlyTwoOrangesInShoppingCartItemCalculateDiscountReturnsZeroDiscount() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productOrange, 1, new BigDecimal("0.25", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("0.00", mathcontext))));
    }

    @Test
    public void whenThreeOrangesInShoppingCartItemCalculateDiscountReturnsDiscountForOneItem() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productOrange, 3, new BigDecimal("0.75", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("0.25"))));
    }

    @Test
    public void whenFiveOrangesInShoppingCartItemCalculateDiscountReturnsDiscountForOneItem() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productOrange, 5, new BigDecimal("1.25", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("0.25"))));
    }

    @Test
    public void when101OrangesInShoppingCartItemCalculateDiscountReturnsDiscountFora33Oranges() throws ShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, "cart-itemId", productOrange, 101, new BigDecimal("25.25", mathcontext));

        BigDecimal discountAmount = this.shoppingCartItemDiscountCOR.calculateDiscount(shoppingCartItem);
        assertNotNull(discountAmount);
        assertThat(discountAmount, is(equalTo(new BigDecimal("8.25"))));
    }

}