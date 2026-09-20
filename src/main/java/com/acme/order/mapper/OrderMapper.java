package com.acme.order.mapper;

import com.acme.order.domain.Order;
import com.acme.order.domain.OrderItem;

import java.util.List;

public interface OrderMapper {

    void insertOrder(Order order);

    void insertOrderItems(List<OrderItem> items);

    Order findOrderById(Long id);

    String findOrderStatusById(Long id);
}
