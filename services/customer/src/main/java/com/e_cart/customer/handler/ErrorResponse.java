package com.e_cart.customer.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
