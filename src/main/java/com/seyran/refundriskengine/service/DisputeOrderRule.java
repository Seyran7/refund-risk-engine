package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.domain.model.OrderStatus;
import com.seyran.refundriskengine.service.risk.RiskRule;
import org.springframework.stereotype.Component;

@Component
public class DisputeOrderRule implements RiskRule {
    @Override
    public int calculateRisk(Order order){
        if(order.getStatus()== OrderStatus.DISPUTED){
            return 40;
        }
        return 0;

    }
    @Override
    public int priority() {
        return 0;
    }
}
