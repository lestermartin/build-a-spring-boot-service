package com.acme.order.web;

import com.acme.order.domain.OrderStatus;

public class OrderStatusResponse {

    private Long orderId;
    private OrderStatus status;

    public OrderStatusResponse() {
    }

    public OrderStatusResponse(Long orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
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
}
