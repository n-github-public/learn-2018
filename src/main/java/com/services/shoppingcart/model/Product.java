package com.services.shoppingcart.model;

import java.math.BigDecimal;

/**
 * The Product.
 *
 * Created by Nilesh on 30/01/2018.
 */
public class Product {
    /* Producct Id. */
    private String productId;

    /* The Product Category. */
    private String productCategory;

    /* The Product Description. */
    private String productDescription;

    /* The Product Name. */
    private String productName;

    /** The Discount Type */
    private ProductDiscountType productDiscountType;
    /* The Product Price. */
    private BigDecimal productPrice;

    public Product(String productId, String productCategory, String productDescription, String productName, BigDecimal productPrice) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productName = productName;
        this.productDiscountType = ProductDiscountType.NONE;
        this.productPrice = productPrice;
    }

    public Product(String productId, String productCategory, String productDescription, String productName, ProductDiscountType productDiscountType, BigDecimal productPrice) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productName = productName;
        this.productDiscountType = productDiscountType;
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public ProductDiscountType getProductDiscountType() {
        return productDiscountType;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }


}
