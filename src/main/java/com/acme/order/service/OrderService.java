package com.acme.order.service;

import com.acme.order.domain.Order;
import com.acme.order.domain.OrderItem;
import com.acme.order.domain.OrderStatus;
import com.acme.order.mapper.OrderMapper;
import com.acme.order.web.OrderItemRequest;
import com.acme.order.web.OrderResponse;
import com.acme.order.web.OrderStatusResponse;
import com.acme.order.web.PlaceOrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final PriceLookup priceLookup;

    public OrderService(OrderMapper orderMapper, PriceLookup priceLookup) {
        this.orderMapper = orderMapper;
        this.priceLookup = priceLookup;
    }

    @Transactional
    public OrderResponse placeOrder(PlaceOrderRequest request) {
        List<OrderItem> items = toOrderItems(request.getItems());

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setStatus(OrderStatus.PLACED.name());
        order.setTotal(sumTotal(items));

        orderMapper.insertOrder(order);                       // id is generated and set back onto `order`
        items.forEach(item -> item.setOrderId(order.getId()));
        orderMapper.insertOrderItems(items);

        return new OrderResponse(order.getId(), OrderStatus.PLACED, order.getTotal());
    }

    public OrderStatusResponse getOrderStatus(Long orderId) {
        String status = orderMapper.findOrderStatusById(orderId);
        if (status == null) {
            throw new OrderNotFoundException(orderId);
        }
        return new OrderStatusResponse(orderId, OrderStatus.valueOf(status));
    }

    private List<OrderItem> toOrderItems(List<OrderItemRequest> requested) {
        return requested.stream().map(req -> {
            OrderItem item = new OrderItem();
            item.setProductId(req.getProductId());
            item.setQuantity(req.getQuantity());
            item.setPrice(priceLookup.currentPriceFor(req.getProductId())); // never trust a client-supplied price
            return item;
        }).toList();
    }

    private BigDecimal sumTotal(List<OrderItem> items) {
        return items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
