package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutServiceTest {
    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        PriceValidatorService priceValidatorService = new PriceValidatorService();
        checkoutService = new CheckoutService(priceValidatorService);
    }

    @Test
    void testCheckoutItems_6() {
        Cart cart = DataSet.createCart(6);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void testCheckoutItems_12() {
        Cart cart = DataSet.createCart(12);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void testCheckoutItems_25() {
        Cart cart = DataSet.createCart(25);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }
}