package com.seyran.refundriskengine.service.analysis;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiskAnalysisService {
    private final OrderRepository orderRepository;
    private final RiskEngine riskEngine;


    public int analyzeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order with id "+orderId+" not found"));

        return riskEngine.calculateTotalRisk(order);
    }


}
