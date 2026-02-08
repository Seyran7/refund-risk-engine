package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.risk.RiskRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HighAmountRule implements RiskRule {

    private static final BigDecimal THRESHOLD = BigDecimal.valueOf(1000);

    @Override
    public int calculateRisk (Order order){
        if(order.getTotalAmount().compareTo(THRESHOLD)>0){
            return 20;
        }
        return 0;
    }
    @Override
    public int priority() {
        return 2;
    }
}
