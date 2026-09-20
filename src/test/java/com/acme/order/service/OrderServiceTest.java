package com.acme.order.service;

import com.acme.order.domain.OrderStatus;
import com.acme.order.mapper.OrderMapper;
import com.acme.order.web.OrderStatusResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderMapper orderMapper;

    @Mock
    PriceLookup priceLookup;

    @InjectMocks
    OrderService orderService;

    @Test
    void returnsStatusWhenOrderExists() {
        when(orderMapper.findOrderStatusById(1L)).thenReturn("PLACED");

        OrderStatusResponse response = orderService.getOrderStatus(1L);

        assertThat(response.getStatus()).isEqualTo(OrderStatus.PLACED);
    }

    @Test
    void throwsWhenOrderMissing() {
        when(orderMapper.findOrderStatusById(99L)).thenReturn(null);

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderStatus(99L));
    }
}
