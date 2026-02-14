package com.seyran.refundriskengine.mapper;

import com.seyran.refundriskengine.domain.model.Buyer;
import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.domain.model.OrderStatus;
import com.seyran.refundriskengine.dto.OrderRequestDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequestDto request) {
        Buyer buyer = Buyer.builder()
                .email(request.getBuyer().getEmail())
                .totalOrders(request.getBuyer().getTotalOrders())
                .totalRefunds(request.getBuyer().getTotalRefunds())
                .build();
        return Order.builder()
                .buyer(buyer)
                .totalAmount(request.getTotalAmount())
                .status(OrderStatus.valueOf(request.getOrderStatus()))
                .build();
    }
}
