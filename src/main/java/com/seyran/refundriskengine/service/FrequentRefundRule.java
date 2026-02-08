package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.risk.RiskRule;
import org.springframework.stereotype.Component;

@Component
public class FrequentRefundRule implements RiskRule {
    @Override
    public int calculateRisk(Order order){
        if (order.getBuyer().getTotalRefunds()>=3){
            return 30;
        }
        return 0;
    }
    @Override
    public int priority() {
        return 1;
    }
}
