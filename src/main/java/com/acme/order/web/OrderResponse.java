package com.acme.order.web;

import com.acme.order.domain.OrderStatus;

import java.math.BigDecimal;

public class OrderResponse {

    private Long orderId;
    private OrderStatus status;
    private BigDecimal total;

    public OrderResponse() {
    }

    public OrderResponse(Long orderId, OrderStatus status, BigDecimal total) {
        this.orderId = orderId;
        this.status = status;
        this.total = total;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
