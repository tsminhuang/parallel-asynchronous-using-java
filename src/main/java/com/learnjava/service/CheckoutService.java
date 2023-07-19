package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.CommonUtil.timeTaken;

public class CheckoutService {

    private final PriceValidatorService priceValidatorService;

    public CheckoutService(final PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(final Cart cart) {

        startTimer();

        List<CartItem> priceValidationList = cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> {
                    boolean isPriceValid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceValid);
                    return cartItem;
                })
                .filter(CartItem::isExpired).toList();

        timeTaken();
        stopWatchReset();

        if (!priceValidationList.isEmpty()) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }

        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }
}
