package com.acme.order.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Stands in for a real product catalog service. Returns a deterministic price
 * per product id so the demo has stable, repeatable totals. Replace with a
 * real catalog lookup (a REST client, another mapper, etc.) in a production system.
 */
@Component
public class PriceLookup {

    public BigDecimal currentPriceFor(Long productId) {
        long base = 10 + (productId % 40);
        return BigDecimal.valueOf(base).setScale(2);
    }
}
