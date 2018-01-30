package com.services.shoppingcart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Application Configuration.
 *
 * Created by Nilesh on 30/01/2018.
 */
@Configuration
@ComponentScan(basePackages = {"com.services.shoppingcart.*"})
public class ShoppingCartSpringConfig {
}
